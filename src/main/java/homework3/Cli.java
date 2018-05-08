package homework3;

import homework3.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Cli {
    private Scanner scanner;
    private boolean end;
    private CrudDao<Developer> daoDeveloper;
    private CrudDao<Project> daoProject;
    private CrudDao<Company> daoCompany;
    private CrudDao<Customer> daoCustomer;
    private CrudDao<Skill> daoSkill;


    private static final String HELPER ="addDeveloper firstName lastName age sex(male/female) idCompany salary  -добавления нового разработчика в базу\n"
            + "getDeveloper id     -просмотр информации про разработчика по id\n"
            +"deleteDeveloper id   -удаление разработчика по id\n"
            +"updateDevSalary id salary  -обновления зарплаты у разработчика\n"
            +"getProject id   -просмотр информации про проект по id\n"
            +"getAllCompany     -вывести список всех компаний \n"
            +"getAllCustomer       -вывести список всех заказчиков\n"
            +"exit       - выход.";

    public Cli(){
        this.scanner = new Scanner(System.in);
        this.end=false;
        this.daoDeveloper = new CrudDao<>(Developer.class);
        this.daoProject = new CrudDao<>(Project.class);
        this.daoCompany = new CrudDao<>(Company.class);
        this.daoCustomer = new CrudDao<>(Customer.class);
        this.daoSkill = new CrudDao<>(Skill.class);
    }

    public void start(){
        System.out.println(HELPER);
        while (!this.end){
            String command = scanner.nextLine();
            String[] arr =command.split("\\s+");
            command = arr[0];
            String[] args = new String[arr.length-1];
            System.arraycopy(arr,1,args,0,arr.length-1);
            this.handleCommand(command,args);

        }
    }

    private void handleCommand(String command, String[] args) {
        switch (command){
            case  "addDeveloper":
                addDeveloperHandle(args);
                break;
            case  "getDeveloper":
                getDeveloperHandle(args);
                break;
            case  "deleteDeveloper":
                deleteDeveloperHandle(args);
                break;
            case  "updateDevSalary":
                updateDevSalaryHandle(args);
                break;
            case  "getProject":
                this.getProjectHandle(args);
                break;
            case  "getAllCompany":
                this.getAllCompany();
                break;
            case  "getAllCustomer":
                this.getAllCustomer();
                break;
            case  "exit":
                this.end=true;
                HibernateUtils.getInstance().close();
                break;
            default:
                System.out.println("неизвестная команда!!!");

        }
    }

    private void getAllCustomer() {
        List<Customer> customers = this.daoCustomer.listAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void getAllCompany() {
        List<Company> companies = this.daoCompany.listAll();
        for (Company company : companies) {
            System.out.println(company);
        }
    }

    private void getProjectHandle(String[] args) {
        if (args.length==1){
            System.out.println(this.daoProject.getById(Long.parseLong(args[0])));
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void updateDevSalaryHandle(String[] args) {
        if (args.length==2){
           Developer  developer = this.daoDeveloper.getById(Long.parseLong(args[0]));
           developer.setSalary(Integer.parseInt(args[1]));
           this.daoDeveloper.update(developer);
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void deleteDeveloperHandle(String[] args) {
        if (args.length==1){
            Developer developer = this.daoDeveloper.getById(Long.parseLong(args[0]));
            this.daoDeveloper.delete(developer);
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void getDeveloperHandle(String[] args) {
        if (args.length==1){
            System.out.println(this.daoDeveloper.getById(Long.parseLong(args[0])));
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void addDeveloperHandle(String[] args) {
        if (args.length==6){
                Developer developer = new Developer();
                developer.setFirstName(args[0]);
                developer.setLastName(args[1]);
                developer.setAge(Integer.parseInt(args[2]));
                developer.setSex(args[3]);
                Company company = this.daoCompany.getById(Long.parseLong(args[4]));
                developer.setCompany(company);
                developer.setSalary(Integer.parseInt(args[5]));
                this.daoDeveloper.create(developer);

        }else {
            System.out.println("не правильное количество аргументов!!!");
        }

    }
}
