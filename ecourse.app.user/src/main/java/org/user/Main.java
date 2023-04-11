package org.user;

public class Main {
    public static void main(String[] args) {
        System.out.println("Modulo User a correr!");

        try{
            GrupoAutomovel grupoAutomovel = new GrupoAutomovel("teste", 4, "C");
            GrupoAutomovelRepositorioJPAImpl ga = new GrupoAutomovelRepositorioJPAImpl();
            ga.add(grupoAutomovel);
        } catch (Exception e){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
            System.out.println();
            System.out.println();
        }

        while(true){
        }
    }
}