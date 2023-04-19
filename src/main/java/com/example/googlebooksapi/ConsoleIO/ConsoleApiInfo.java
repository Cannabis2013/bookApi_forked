package com.example.googlebooksapi.ConsoleIO;

public class ConsoleApiInfo {
    public static void print()
    {
        printWelcome();
        printApiEndpoints();

    }

    private static void printWelcome()
    {
        System.out.println();
        System.out.println("Movie Service Api v0.1 ");
        System.out.println();
    }

    private static void printApiEndpoints()
    {
        System.out.println("Available endpoints:");
        System.out.println();
        printEndPoint("","SwaggerUI/OpenAPI interface");
    }

    private static void printEndPoint(String path, String description)
    {
        var domain = "\t http://localhost:8080/";

        System.out.println(domain + path + " : " + description);
    }
}
