package com.cjm.ms;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Exceptions handling.
//        System.out.println("BEGIN: Exception handling");
//
//        System.out.println("The result of 50/0 is: ");
//        System.out.println(divideLBYL(50, 0));
//        System.out.println();
//
//        System.out.println("The result of 50/0 is: ");
//        System.out.println(divideEAFP(50, 0));
//        System.out.println();
//
//        System.out.println("User input:");
//        System.out.println(getInt());
//        System.out.println();
//
//        System.out.println("User input:");
//        System.out.println(getIntLBYL());
//        System.out.println();
//
//        System.out.println("User inputs:");
//        try {
//            System.out.println(userDivide());
//        } catch (ArithmeticException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println();

        // IO.
//        System.out.println("BEGIN: IO");
//        createAdventureGame();
//         moreNIO();
        // usePipes();
        modifyFilesystem();
    }
    /*
        Uses 'look before you leap' concept in which you ensure
        that the arguments are valid before using them.
     */
    private static int divideLBYL(int x, int y) {
        // Division by zero is undefined.
        if(y != 0) {
            return x/y;
        } else {
            System.out.println("Can't divide by zero.");
            // Returning 0 is technically incorrect.
            return 0;
        }
    }
    /*
        Uses 'easy to ask for forgiveness than permission' concept
        in which you execute code then deal with potential fallout.
     */
    private static int divideEAFP(int x, int y) {
        try {
            return x/y;
        } catch(ArithmeticException e) {
            System.out.println("Can't divide by zero.");
            return 0;
        }
    }
    // Gets input from user. Expects integer, otherwise will crash.
    private static int getInt() {
        System.out.print("Enter an integer (no validation, might crash): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    // Validates input. Returns 0 for invalid input.
    private static int getIntLBYL() {
        System.out.print("Enter an integer (validated, won't crash): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for(char symbol : input.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                System.out.println("Invalid input.");
                return 0;
            }
        }
        System.out.println("Valid input.");
        return Integer.parseInt(input);
    }
    // Also validates input, but uses exceptions.
    private static int getIntEAFP() {
        System.out.print("Enter an integer (validated, won't crash): ");
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        try {
            value = scanner.nextInt();
            System.out.println("Valid input.");
        } catch(InputMismatchException e) {
            System.out.println("Invalid input.");
        }
        return value;
    }
    // Practice handling multiple exceptions.
    private static double userDivide() {
        double value = 0;
        boolean isValid = false;
        // Forces user to enter valid input.
        while(!isValid) {
            try {
                double x = getInt();
                double y = getInt();
                value = x/y;
                isValid = true;
            } catch(InputMismatchException e) { // Can catch multiple exceptions in one statement using |.
                System.out.println("Invalid input: Only digits are accepted.");
            } catch(ArithmeticException e) {
                // Propagates exception to allow caller to deal with it.
                throw new ArithmeticException("Invalid input: Can't divide by zero.");
            }
        }
        System.out.println("Valid input.");
        return value;
    }
    // Practice working with input and output data using IO and NIO packages.
    private static void createAdventureGame() throws IOException {
        System.out.println("\nBEGIN: createAdventureGame");

        Locations locations;
        try {
            locations = new Locations();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        Location currentLocation = locations.get(64);
        while(true) {
            System.out.println(currentLocation.getDescription());

            if(currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                currentLocation = locations.get(currentLocation.getExits().get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();
    }
    // Practice working with data input and data output using more of the NIO package.
    private static void moreNIO() {
        System.out.println("\nBEGIN: moreNIO");
        try {
//            FileInputStream file = new FileInputStream("data.txt");
//            // FileChannel is input or output only depending on which FileXStream is used.
//            FileChannel channel = file.getChannel(); // Input only.
            // Write and read data using Files class.
            Path dataPath = FileSystems.getDefault().getPath("data.txt");
            Files.write(dataPath, "\nLine x".getBytes(), StandardOpenOption.APPEND);
            // Use Files to sequentially read or write data using NIO.
            List<String> lines = Files.readAllLines(dataPath);
            for(String line: lines) {
                System.out.println(line);
            }

            // Write data using file channel.
            FileOutputStream binFile = new FileOutputStream("data.dat");
            FileChannel binChannel = binFile.getChannel();
            byte[] outputBytes = "Hello, World!".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes); // Resets position to 0.
            binChannel.write(buffer);

            // Write integer data using file channel.
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip(); // Resets position to 0.
            binChannel.write(intBuffer);

            // Do it again, but overwrite the contents of the buffer first.
            intBuffer.flip(); // Have to flip due to writing buffer contents.
            intBuffer.putInt(-98765); // New integer value overwrites old integer value.
            intBuffer.flip(); // Have to flip again since we wrote to the buffer.
            binChannel.write(intBuffer);

            // Read data using IO.
            RandomAccessFile raIO = new RandomAccessFile("data.dat", "rwd");
            byte[] b = new byte[outputBytes.length];
            raIO.read(b);
            System.out.println(new String(b));

            long int1 = raIO.readInt();
            long int2 = raIO.readInt();
            System.out.println(int1);
            System.out.println(int2);
            raIO.close();

            // Read data using NIO.
            RandomAccessFile raNIO = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = raNIO.getChannel();
            long numBytesRead = channel.read(buffer);
            if(buffer.hasArray()) {
                System.out.println("byte buffer = " + new String(buffer.array()));
            }

            // Use either absolute or relative. Using both will be confusing.
            // Absolute read.
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
//            // Relative read.
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());

            // Write to large buffer.
            buffer = ByteBuffer.allocate(100);
            outputBytes = "Hello, World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you".getBytes();
            buffer.put(outputBytes);
            long int1Pos = outputBytes.length;
            buffer.putInt(245);
            long int2Pos = int1Pos + Integer.BYTES;
            buffer.putInt(-98765);
            buffer.put(outputBytes2);
            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
            buffer.putInt(1000);
            // Equivalent:
            // buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);
            buffer.flip();
            binChannel.write(buffer);

            // Read bytes in reverse order.
            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
            channel.position(int3Pos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int3 = " + readBuffer.getInt());

            channel.position(int2Pos);
            readBuffer.flip();
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int2 = " + readBuffer.getInt());

            channel.position(int1Pos);
            readBuffer.flip();
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int1 = " + readBuffer.getInt());

            // Copy file.
            RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat", "rw");
            FileChannel copyChannel = copyFile.getChannel();
            channel.position(0);
            // Uses position relative to current position, rather than absolute position.
            long numTransferred = copyChannel.transferFrom(channel, 0, channel.size());
            // Identical.
            // long numTransferred = channel.transferTo(0, channel.size(), copyChannel);
            System.out.println("Num transferred = " + numBytesRead);

            copyChannel.close();

            //
            byte[] outputString = "Hello, World!".getBytes();
            long str1Pos = 0;
            long newInt1Pos = outputString.length;
            long newInt2Pos =  newInt1Pos + Integer.BYTES;
            byte[] outputString2 = "Nice to meet you".getBytes();
            long str2Pos = newInt2Pos + Integer.BYTES;
            long newInt3Pos = str2Pos + outputString2.length;

            intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip();
            binChannel.position(newInt1Pos);
            binChannel.write(intBuffer);

            intBuffer.flip();
            intBuffer.putInt(-98765);
            intBuffer.flip();
            binChannel.position(newInt2Pos);
            binChannel.write(intBuffer);

            intBuffer.flip();
            intBuffer.putInt(1000);
            intBuffer.flip();
            binChannel.position(newInt3Pos);
            binChannel.write(intBuffer);

            binChannel.position(str1Pos);
            binChannel.write(ByteBuffer.wrap(outputString));
            binChannel.position(str2Pos);
            binChannel.write(ByteBuffer.wrap(outputString2));

            //
            readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);
            readBuffer.flip();
            byte[] inputString = new byte[outputBytes.length];
            readBuffer.get(inputString);
            System.out.println("inputString = " + new String(inputString));
            System.out.println("int1 = " + readBuffer.getInt());
            System.out.println("int2 = " + readBuffer.getInt());
            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString2);
            System.out.println("inputString2 = " + new String(inputString2));
            System.out.println("int3 = " + readBuffer.getInt());

            channel.close();
            raNIO.close();
            binChannel.close();
            binFile.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    // Practice using pipes to send data from one thread to another.
    private static void usePipes() {
        System.out.println("\nBEGIN: usePipes");

        try {
            Pipe pipe = Pipe.open();

            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for (int i=0; i<10; i++) {
                            String currentTime = "The time is: " + System.currentTimeMillis();
                            buffer.put(currentTime.getBytes());
                            buffer.flip();
                            while(buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for(int i=0; i<10; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread: " + new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            new Thread(writer).start();
            new Thread(reader).start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    // Practice working with the file system - copy, move, delete, etc.
    private static void printFile(Path path) {
        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private static void modifyFilesystem() {
        System.out.println("\nBEGIN: modifyFilesystem");

        Path wdPath = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
        printFile(wdPath);
        System.out.println();

        // Equivalent:
        //Path subDirPath = FileSystems.getDefault().getPath("files\\SubdirectoryFile.txt");
        //Path subDirPath = FileSystems.getDefault().getPath("files", "SubdirectoryFile.txt");
        //Path subDirPath = FileSystems.getDefault().getpath(".", "files", "..", "files", "SubdirectoryFile.txt");
        Path subDirPath = Paths.get(".", "files", "SubdirectoryFile.txt"); // '.' is working directory.
        printFile(subDirPath);
        System.out.println();

        // Equivalent:
        // Path outTherePath = Paths.get(
        // "D:\\Personal\\My Documents\\~Projects\\IntelliJ IDEA\\CompleteJavaMasterclass\\source\\OutThere.txt");
        // Path outTherePath = Paths.get("D:\\Personal\\My Documents\\~Projects\\IntelliJ IDEA\\",
        // CompleteJavaMasterclass\\source\\OutThere.txt");
        Path outTherePath = FileSystems.getDefault().getPath("..", "OutThere.txt");
        printFile(outTherePath);
        System.out.println("Does file exist? " + Files.exists(outTherePath) + ".");
        System.out.println();

        Path tempDirectory = Paths.get(".");
        System.out.println("Working directory: " + tempDirectory.toAbsolutePath());
        tempDirectory = Paths.get(".", "files", "..", "files", "SubdirectoryFile.txt");
        System.out.println("Temp directory: " + tempDirectory.toAbsolutePath());
        System.out.println("After normalisation: " + tempDirectory.normalize().toAbsolutePath());
        System.out.println();

        Path badFilePath = FileSystems.getDefault().getPath("ThisFileDoesNotExist.txt");
        System.out.println("Bad path: " + badFilePath.toAbsolutePath());
        System.out.println("Does file exist: " + Files.exists(badFilePath) + ".");
        System.out.println();

        try {
            // Copy file.
            Path source = Paths.get(".", "Examples", "file1.txt");
            Path target = Paths.get(".", "Examples", "file1copy.txt");
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            // Copy directory.
            source = Paths.get(".", "Examples", "Dir1");
            target = Paths.get(".", "Examples", "Dir1Copy");
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            // Move file.
            source = Paths.get(".", "Examples", "file1copy.txt");
            target = Paths.get(".", "Examples", "Dir1", "file1copy.txt");
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            // Rename file.
            source = Paths.get(".", "Examples", "Dir1", "file1copy.txt");
            target = Paths.get(".", "Examples", "Dir1", "file1copy_renamed.txt");
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            // Delete file.
            target = Paths.get(".", "Examples", "Dir1", "file1copy_renamed.txt");
            Files.deleteIfExists(target);
            // Create file and delete it.
            target = Paths.get(".", "Examples", "file2.txt");
            Files.createFile(target);
            Files.delete(target);
            // Create directory and delete it.
            target = Paths.get(".", "Examples", "Dir4");
            Files.createDirectory(target);
            Files.delete(target);
            // Create directories.
            target = Paths.get(".", "Examples", "Dir2\\Dir3\\Dir4\\Dir5\\Dir6");
            Files.createDirectories(target);
            // Get file metadata.
            target = Paths.get(".", "Examples", "Dir1\\file1.txt");
            long size = Files.size(target);
            System.out.println("file1 size: " + size);
            System.out.println("file1 last modified: " + Files.getLastModifiedTime(target));
            BasicFileAttributes attributes = Files.readAttributes(target, BasicFileAttributes.class);
            System.out.println("file1 size: " + attributes.size());
            System.out.println("file1 last modified: " + attributes.lastModifiedTime());
            System.out.println("file1 created: " + attributes.creationTime());
            System.out.println("file1 is directory? " + attributes.isDirectory());
            System.out.println("file1 is file? " + attributes.isRegularFile());
            System.out.println();
            // Read directory contents, then filter for regular files only.
            DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
                public boolean accept(Path path) throws IOException {
                    return Files.isRegularFile(path);
                }
            };
            source = Paths.get(".", "Examples\\Dir2");
            try(DirectoryStream<Path> contents = Files.newDirectoryStream(source, filter)) {
                for(Path content: contents) {
                    System.out.println(content.getFileName());
                }
            } catch(IOException | DirectoryIteratorException e) {
                e.printStackTrace();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
