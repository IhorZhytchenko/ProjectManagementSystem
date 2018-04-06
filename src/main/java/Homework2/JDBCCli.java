package Homework2;

import Homework2.entity.Developer;
import Homework2.entity.Project;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JDBCCli {
    private Scanner scanner;
    private JDBCManager manager;
    private boolean end;
    private static final String HELPER ="addDeveloper firstName lastName age sex(male/female) idCompany salary  -добавления нового разработчика в базу\n"
            + "getDeveloper id     -просмотр информации про разработчика по id\n"
            +"deleteDeveloper id   -удаление разработчика по id\n"
            +"updateDevSalary id salary  -обновления зарплаты у разработчика\n"
            +"getSalaryDevelopersByProject id   -вывести зарплату(сумму) всех разработчиков отдельного проекта\n"
            +"getDevelopersByProject id     -вывести список разработчиков отдельного проекта\n"
            +"getJavaDevelopers       -вывести список всех Java разработчиков\n"
            +"getMiddleDevelopers      -вывести список всех middle разработчиков\n"
            +"getProjects      -вывести cписок проектов в следующем формате:  название проекта - описание -количество разработчиков на этом проекте\n"
            +"exit       - выход.";

    public JDBCCli(){
        scanner = new Scanner(System.in);
        manager = new JDBCManager();
        end=false;
    }

    public void start(){
        System.out.println(HELPER);
        while (!end){
            String command = scanner.nextLine();
            String[] arr =command.split("\\s+");
            command = arr[0];
            String[] args = new String[arr.length-1];
            System.arraycopy(arr,1,args,0,arr.length-1);
            handleCommand(command,args);

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
            case  "getSalaryDevelopersByProject":
                getSalaryDevelopersByProjectHandle(args);
                break;
            case  "getDevelopersByProject":
                getDevelopersByProjectHandle(args);
                break;
            case  "getJavaDevelopers":
                getJavaDevelopersHandle();
                break;
            case  "getMiddleDevelopers":
                getMiddleDevelopersHandle();
                break;
            case  "getProjects":
                getProjectsHandle();
                break;
            case  "exit":
                end=true;
                break;
            default:
                System.out.println("неизвестная команда!!!");

        }
    }

    private void getProjectsHandle() {
        Map<Project,Integer> projects =manager.getProjectWithDevelopersCount();
        for(Map.Entry<Project, Integer> project : projects.entrySet()){
            System.out.println(project.getKey()+", developers count -"+project.getValue());
        }
    }

    private void getMiddleDevelopersHandle() {
        List<Developer> developers = manager.getMiddleDevelopers();
        for(Developer developer:developers){
            System.out.println(developer);
        }
    }

    private void getJavaDevelopersHandle() {
        List<Developer> developers = manager.getJavaDevelopers();
        for(Developer developer:developers){
            System.out.println(developer);
        }
    }

    private void getDevelopersByProjectHandle(String[] args) {
        if (args.length==1){
            try {
                long id =Long.parseLong(args[0]);
                List<Developer> developers = manager.getDevelopersByProjectId(id);
                for(Developer developer:developers){
                    System.out.println(developer);
                }

            } catch (Exception e){
                System.out.println("не коректный id!!!");
            }
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void getSalaryDevelopersByProjectHandle(String[] args) {
        if (args.length==1){
            try {
                long id =Long.parseLong(args[0]);
               int cost = manager.getCostProjectById(id);
                System.out.println(cost);
            } catch (Exception e){
                System.out.println("не коректный id!!!");
            }
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void updateDevSalaryHandle(String[] args) {
        if (args.length==2){
            try {
                long id =Long.parseLong(args[0]);
                int salary=Integer.parseInt(args[1]);
                manager.updateSalaryById(id,salary);
            } catch (Exception e){
                System.out.println("не коректные значения!!!");
            }
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void deleteDeveloperHandle(String[] args) {
        if (args.length==1){
            try {
                long id =Long.parseLong(args[0]);
                manager.deleteDeveloper(id);
            } catch (Exception e){
                System.out.println("не коректный id!!!");
            }
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void getDeveloperHandle(String[] args) {
        if (args.length==1){
            try {
                long id =Long.parseLong(args[0]);
                Developer developer = manager.getDeveloperById(id);
                System.out.println(developer);

            } catch (Exception e){
                System.out.println("не коректный id!!!");
            }
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }
    }

    private void addDeveloperHandle(String[] args) {
        if (args.length==6){
            try {
                Developer developer = new Developer();
                developer.setFirstName(args[0]);
                developer.setLastName(args[1]);
                developer.setAge(Integer.parseInt(args[2]));
                developer.setSex(args[3]);
                developer.setIdCompany(Integer.parseInt(args[4]));
                developer.setSalary(Integer.parseInt(args[5]));
                manager.addDeveloper(developer);
            } catch (Exception e){
                System.out.println("не коректные значения!!!");
            }
        }else {
            System.out.println("не правильное количество аргументов!!!");
        }

    }

}
