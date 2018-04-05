package Homework2;

import java.util.Arrays;
import java.util.Scanner;

public class JDBCCli {
    private Scanner scanner;
    private JDBCManager manager;
    private boolean end;
    private static final String HELPER ="addDeveloper firstName lastName age sex idCompany salary  -добавления нового разработчика в базу\n"
            + "getDeveloper id     -просмотр информации про разработчика по id\n"
            +"deleteDeveloper id   -удаление разработчика по id\n"
            +"updateDevSalary id salary  -обновления зарплаты у разработчика\n"
            +"getSalaryDevelopersByProject id   -вывести зарплату(сумму) всех разработчиков отдельного проекта\n"
            +"getDevelopersByProject id     -вывести список разработчиков отдельного проекта\n"
            +"getJavaDevelopers       -вывести список всех Java разработчиков\n"
            +"getMiddleDevelopers      -вывести список всех middle разработчиков\n"
            +"";

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

    }

}
