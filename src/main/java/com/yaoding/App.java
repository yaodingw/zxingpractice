package com.yaoding;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
//        int a=1257;
//        byte b=(byte)a;
//        System.out.println("b+"+b);
//        System.out.println( "Hello World!" );
        test();

    }

    public static void test(){
        List<Goods> tst=new ArrayList<>();
        for(int i=0;i<50;i++){
            tst.add(new Goods(i+"",new Random().nextInt(30)));
        }

        System.out.println(tst.size());
        for(Goods goods:tst){
            System.out.println(goods);
        }

        System.out.println("---------------start----j");

        Iterator<Goods> ter=tst.iterator();
        while(ter.hasNext()){
            Goods curr=ter.next();
            if(curr.getAge()>10&&curr.getAge()<15){
                ter.remove();
            }
        }

        System.out.println(tst.size());
        for(Goods goods:tst){
            System.out.println(goods);
        }



    }
}

class Goods{
    private String name;

    private int age;

    public Goods(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}