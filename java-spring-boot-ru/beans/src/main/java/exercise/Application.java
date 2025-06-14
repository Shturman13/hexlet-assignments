package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    static class DayCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            int hour = LocalDateTime.now().getHour();
            return hour >= 6 && hour < 22;
        }
    }

    // Условие для создания бина Night (22:00 - 5:59)
    static class NightCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            int hour = LocalDateTime.now().getHour();
            return hour >= 22 || hour < 6;
        }
    }

    @Bean
    @Scope("prototype")
    @Conditional(DayCondition.class)
    public Daytime day() {
        return new Day();
    }

    @Bean
    @Scope("prototype")
    @Conditional(NightCondition.class)
    public Daytime night() {
        return new Night();
    }
    // END
}
