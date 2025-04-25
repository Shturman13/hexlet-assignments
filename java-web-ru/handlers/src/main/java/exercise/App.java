package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {
        var phones = Data.getPhones();
        var domains = Data.getDomains();

        // BEGIN
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/phones", ctx -> {
            ctx.json(phones);
        });

        app.get("/domains", ctx -> {
            ctx.json(domains);
        });
        return app;


        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
