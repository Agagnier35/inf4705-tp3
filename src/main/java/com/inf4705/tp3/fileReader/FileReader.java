package com.inf4705.tp3.fileReader;

import com.inf4705.tp3.exception.InvalidDataSetException;
import com.inf4705.tp3.model.Destination;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileReader {
    private Scanner scanner;

    public FileReader(String path) throws IOException {
        File file = new File(path);
        scanner = new Scanner(file);
    }

    public DecryptedFile readFile() throws InvalidDataSetException {
        int nbDestinations = readNumberOfDestinations();
        List<Destination> destinations = readDestinationsAndLinks(nbDestinations);
        int timeLimit = readTimeLimit();
        List<Integer> appreciations = readAppreciationsValues(nbDestinations);

        DecryptedFile file = new DecryptedFile();

        file.setTimeLimit(timeLimit);
        IntStream.range(0, destinations.size())
                .forEach(i -> destinations.get(i).setAppreciation(appreciations.get(i)));

        file.setDestination(destinations);
        return file;
    }

    private int readNumberOfDestinations() throws InvalidDataSetException {
        validateScanner("The file is empty");
        return Integer.parseInt(readLine());
    }

    private List<Destination> readDestinationsAndLinks(int nbDestinations) throws InvalidDataSetException {
        //create N "blank" destinations
        List<Destination> destinations = initDestinations(nbDestinations);

        //foreach Destination(row)
        for (int currentDestinationPos = 0; currentDestinationPos < nbDestinations; currentDestinationPos++) {
            Destination currentDestination = destinations.get(currentDestinationPos);

            //read links row
            validateScanner("The number of destinations read is smaller than the expected number");
            String line = readLine();
            List<Integer> distancesToDestination = Arrays.stream(line.split("\\s+", nbDestinations))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            //foreach destination(column) create a link
            for (int i = 0; i < distancesToDestination.size(); i++) {
                if (i == currentDestinationPos) {//ignore itself(0 diagonal)
                    continue;
                }
                Destination linkedDestination = destinations.get(i);
                currentDestination.addLink(linkedDestination, distancesToDestination.get(i));
            }
        }
        return destinations;
    }

    private List<Destination> initDestinations(int nbDestinations) {
        List<Destination> destinations = new ArrayList<>(nbDestinations);
        int destInit = 0;
        while (destInit < nbDestinations) {
            destinations.add(new Destination(destInit++));
        }
        return destinations;
    }

    private int readTimeLimit() throws InvalidDataSetException {
        validateScanner("Missing time limit");
        return Integer.parseInt(readLine());
    }

    private List<Integer> readAppreciationsValues(int nbDestinations) throws InvalidDataSetException {
        validateScanner("Missing appreciation values");
        String appreciationValues = readLine();
        String[] appreciation = appreciationValues.split("\\s+", nbDestinations);
        return Arrays.stream(appreciation)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }

    private void validateScanner(String failureMessage) throws InvalidDataSetException {
        if (!scanner.hasNextLine()) {
            throw new InvalidDataSetException(failureMessage);
        }
    }
}
