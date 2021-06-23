package test;

import dominio.Estudiante;
import java.util.Scanner;
import java.util.Arrays;

public class test {

    static Scanner datos = new Scanner(System.in);

    public static void main(String[] args) {

        int nroHmbres, ne;
        System.out.println("Cuántos estudiantes desea ingresar:");
        int n = datos.nextInt();

        Estudiante[] estudiantes = new Estudiante[n];

        llenaEstudiante(estudiantes);
        nroHmbres = porcentajeGenero(estudiantes);
        System.out.println("El listado de estudiantes ordenados de mayor a menor nota");
        //llamar al metodo para ordenar 
        for (Estudiante est : estudiantes) {
            ordmenorMayor(estudiantes);
            System.out.println(est);

        }
        System.out.println("El porcentaje de Hombres por encima del promedio : " + porentajePromedio(estudiantes, nroHmbres, 'm'));
        System.out.println("El porcentaje de Mujeres por encima del promedio : " + porentajePromedio(estudiantes, nroHmbres, 'f'));
        ne = notaAlta(estudiantes);
        System.out.println("El estudiante con la màs nota alta es:" + estudiantes[ne].getNombre());

    }

    public static void llenaEstudiante(Estudiante est[]) {
        String nombre;
        char sexo;
        double nota;
        for (int i = 0; i < est.length; i++) {
            datos.nextLine();
            System.out.println("Estudiante Nro :" + (i + 1));
            System.out.println("Nombre: ");
            nombre = datos.nextLine();
            System.out.println("Sexo : <<m>><<f>>");
            sexo = datos.next().charAt(0);
            System.out.println("Ingrese nota");
            nota = datos.nextDouble();
            est[i] = new Estudiante(nombre, sexo, nota);
        }
    }

    public static int porcentajeGenero(Estudiante est[]) {
        int cuentahombres = 0, cuentamujeres = 0;
        double porc;
        for (int i = 0; i < est.length; i++) {
            if (est[i].getSexo() == 'm') {
                cuentahombres++;
            }
        }
        cuentamujeres = est.length - cuentahombres;
        System.out.println("Porcentaje Hombre: " + (cuentahombres * 100 / est.length));
        System.out.println("Porcentaje Mujeres: " + (cuentamujeres * 100 / est.length));
        porc = cuentahombres * 100 / est.length;
        return cuentahombres;
    }

    public static double porentajePromedio(Estudiante est[], int homb, char sex) {
        int muj = est.length - homb;
        int cuenta = 0;
        double porch = 0, porcm = 0;
        double promedio = 0;
        for (int i = 0; i < est.length; i++) {
            promedio += est[i].getNota();

        }
        promedio = promedio / est.length;

        for (int i = 0; i < est.length; i++) {
            if (est[i].getNota() > promedio && est[i].getSexo() == sex) {
                cuenta++;
            }
        }
        porch = homb * 100 / est.length;
        porcm = muj * 100 / est.length;

        if (sex == 'm' && homb > 0) {
            porch = cuenta * porch / homb;
            return porch;
        } else if (muj > 0) {
            porcm = cuenta * porcm / muj;
            return porcm;
        } else {
            return 0;
        }
    }

    public static int notaAlta(Estudiante est[]) {

        double mayor;
        int indice = 0;
        mayor = est[0].getNota();
        for (int i = 1; i < est.length; i++) {
            if (est[i].getNota() > mayor) {
                mayor = est[i].getNota();
                indice = i;
            }

        }
        return indice;
    }

    public static void ordmenorMayor(Estudiante est[]) {
        double aux;
        for (int i = 1; i < est.length; i++) {
            if (est[i - 1].getNota() < est[i].getNota()) {
                aux = est[i].getNota();
                est[i].setNota(est[i - 1].getNota());
                est[i - 1].setNota(aux);
            }
        }
    }
}
