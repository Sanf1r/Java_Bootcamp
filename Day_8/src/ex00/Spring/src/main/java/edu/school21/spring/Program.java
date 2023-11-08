package edu.school21.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {

        // An example of code using these classes in a standard way:
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello!");

        System.out.println("------------------------------------------");

        PreProcessor preProcessor2 = new PreProcessorToUpperImpl();
        Renderer renderer2 = new RendererErrImpl(preProcessor2);
        PrinterWithDateTimeImpl printer2 = new PrinterWithDateTimeImpl(renderer2);
        printer2.print("Hello!");

        System.out.println("------------------------------------------");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer3 = context.getBean("printerWithPrefix", Printer.class);
        printer3.print("Hello!");

        System.out.println("------------------------------------------");

        Printer printer4 = context.getBean("printerWithDateTimeStLow", Printer.class);
        printer4.print("Hello!");

        ((ClassPathXmlApplicationContext) context).close();

    }
}
