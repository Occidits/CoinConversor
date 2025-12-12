package db;

public class Moeda {
    String name;
    double salario;

    public Moeda(String name, double salario){
        this.name = name;
        this.salario = salario;
    }

    public String getName(){return name;}
    public double getSalario(){return salario;}
}
