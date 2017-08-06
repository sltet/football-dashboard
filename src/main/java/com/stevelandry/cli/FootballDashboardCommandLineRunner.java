package com.stevelandry.cli;

import com.stevelandry.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Component
@RequiredArgsConstructor
public class FootballDashboardCommandLineRunner implements CommandLineRunner {

    private final CommandService commandService;

    @Override
    public void run(String... strings) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.print("Please enter your commands : ");

        while((input = bufferedReader.readLine()) != null) {
            try {
                commandService.parseInput(input);
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
