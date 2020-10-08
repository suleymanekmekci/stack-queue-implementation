public class InputManager {

    public static void inputTransactions(String fileName) {
        String[] inputLines = ReadFromFile.readFile(fileName);

        String stackOutput = "";
        String queueOutput = "";

        Stack stack = new Stack();
        Queue queue = new Queue();




        String[] stackLines = ReadFromFile.readFile("stack.txt");
        // take stack from stack.txt
        String[] numbersString = stackLines[0].split(" ");

        for (int i = numbersString.length - 1; i >=0 ; i--) {
            stack.push(Integer.parseInt(numbersString[i]));
        }

        String[] queueLines = ReadFromFile.readFile("queue.txt");
        // take queue from queue.txt
        String[] queueNumbersString = queueLines[0].split(" ");

        for (int i = 0; i <=queueNumbersString.length - 1 ; i++) {
            queue.enqueue(Integer.parseInt(queueNumbersString[i]));
        }



        for (String line : inputLines) {
            String transaction = line.split(" ")[1];
            String stackOrQueue = line.split(" ")[0];

            if (transaction.equals("removeGreater")) {
                int number = Integer.parseInt(line.split(" ")[2]);
                if (stackOrQueue.equals("Q")) {
                    queue.removeGreater(number);
                    queueOutput += "After removeGreater " + number + ":\n";
                    queueOutput += queue + "\n";

                }
                else if (stackOrQueue.equals("S")) {
                    stack.removeGreater(number);
                    stackOutput += "After removeGreater " + number + ":\n";
                    stackOutput += stack + "\n";

                }
            }

            else if (transaction.equals("calculateDistance")) {
                if (stackOrQueue.equals("Q")) {
                    queueOutput += "After calculateDistance:" + "\n";
                    queueOutput += "Total distance=" + queue.calculateDistance() +"\n";
                }
                else if (stackOrQueue.equals("S")) {
                    stackOutput += "After calculateDistance:" + "\n";
                    stackOutput += "Total distance=" + stack.calculateDistance() +"\n";

                }

            }
            else if (transaction.equals("addOrRemove")) {
                int number = Integer.parseInt(line.split(" ")[2]);
                if (stackOrQueue.equals("Q")) {
                    queue.addOrRemove(number);
                    queueOutput += "After addOrRemove " + number + ":" +"\n" ;
                    queueOutput += queue + "\n";


                }
                else if (stackOrQueue.equals("S")) {
                    stack.addOrRemove(number);
                    stackOutput += "After addOrRemove " + number + ":" + "\n";
                    stackOutput += stack + "\n";

                }

            }
            else if (transaction.equals("reverse")) {
                int number = Integer.parseInt(line.split(" ")[2]);
                if (stackOrQueue.equals("Q")) {
                    queue.reverse(number);
                    queueOutput += "After reverse " + number + ":" +"\n";
                    queueOutput += queue + "\n";

                }
                else if (stackOrQueue.equals("S")) {
                    stack.reverse(number);
                    stackOutput += "After reverse " + number + ":" + "\n";
                    stackOutput += stack + "\n";

                }

            }

            else if (transaction.equals("sortElements")) {
                if (stackOrQueue.equals("Q")) {
                    queue.sortElements();
                    queueOutput += "After sortElements:"+ "\n";
                    queueOutput += queue + "\n";
                }
                else if (stackOrQueue.equals("S")) {
                    stack.sortElements();
                    stackOutput += "After sortElements:"+ "\n";
                    stackOutput += stack + "\n";
                }

            }
            else if (transaction.equals("distinctElements")) {
                if (stackOrQueue.equals("Q")) {
                    int numberOfDistinctElements = queue.distinctElements();
                    queueOutput += "After distinctElements:" + "\n";
                    queueOutput += "Total distinct element=" + numberOfDistinctElements + "\n";
                }
                else if (stackOrQueue.equals("S")) {
                    int numberOfDistinctElements = stack.distinctElements();
                    stackOutput += "After distinctElements:" + "\n";
                    stackOutput += "Total distinct element=" + numberOfDistinctElements + "\n";

                }

            }


        }

        String stackString = stack.toString();
        String queueString = queue.toString();

        WriteToFile.writeFile(stackOutput,"stackOut.txt");
        WriteToFile.writeFile(queueOutput,"queueOut.txt");
        WriteToFile.writeFile(stackString,"stack.txt");
        WriteToFile.writeFile(queueString,"queue.txt");


    }


}
