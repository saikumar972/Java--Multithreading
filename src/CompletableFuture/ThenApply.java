package CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class ThenApply {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Employee>> list=CompletableFuture.supplyAsync(()->{
            System.out.println("First thread "+Thread.currentThread().getName());
            return EmployeeDatabase.employeeList();
        }).thenApplyAsync((empList)->{
            System.out.println("Second thread "+Thread.currentThread().getName());
            return empList.stream().filter(emp->emp.getCompany().equals("acenture")).toList();
        }).thenApplyAsync((empList)->{
            System.out.println("Third thread "+Thread.currentThread().getName());
            return empList.stream().filter(emp->emp.getTech().equals("python")).toList();
        });
        System.out.println(list.get());
        System.out.println("-----------------------------------------------------");
        CompletableFuture<Void> list2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("First thread " + Thread.currentThread().getName());
            return EmployeeDatabase.employeeList();
        }).thenApplyAsync((empList) -> {
            System.out.println("Second thread " + Thread.currentThread().getName());
            return empList.stream().filter(emp -> emp.getCompany().equals("acenture")).toList();
        }).thenAcceptAsync((empList) -> {
            System.out.println("Third thread " + Thread.currentThread().getName());
            List<Employee> result = empList.stream().filter(emp -> emp.getTech().equals("python")).toList();
            System.out.println(result);
        });
        list2.get();
    }
}
class EmployeeDatabase{
    public static List<Employee> employeeList(){
        return Stream.of(new Employee(1,"sai","java","acenture"),
                new Employee(2,"rajveer","c","tcs"),
                new Employee(3,"Nandu","sap","wipro"),
                new Employee(4,"Athadu","python","acenture"),
                new Employee(5,"Raju","python","acenture")).toList();
    }
}

class Employee{
    private int id;
    private String name;
    private String tech;
    private String company;

    public Employee(int id, String name, String tech, String company) {
        this.id = id;
        this.name = name;
        this.tech = tech;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tech='" + tech + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
