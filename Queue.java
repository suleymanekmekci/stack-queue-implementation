import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {
    private ArrayList<Integer> queue = new ArrayList<>();
    private int size = 0;

    public void reset() {
        queue = new ArrayList<>();
        size = 0;
    }

    @Override
    public String toString() {
        String string = "";

        Queue tempQueue = copy();

        for (int i = 0; i <= size-1; i++) {
            string += tempQueue.dequeue() + " ";
        }
        return string;
    }

    public void enqueue(Integer number) {
        queue.add(0,number);
        size++;
    }

    public Integer dequeue() {
        size--;
        return queue.remove(size);

    }

    public Integer peek() {
        Queue queue = copy();

        return queue.dequeue();
    }

    public Integer size() {
        return size;
    }

    public boolean isEmpty() {
        return size<=0;
    }

    public Queue copy() {
        Queue returnQueue = new Queue();
        Queue tempQueue = new Queue();

        // create new 2 queues and fill them with popping main queue
        while (!isEmpty()) {
            int data = dequeue();
            returnQueue.enqueue(data);
            tempQueue.enqueue(data);
        }

        // refill back the main queue
        while (!tempQueue.isEmpty()) {
            enqueue(tempQueue.dequeue());
        }


        return returnQueue;
    }

    public Integer distinctElements() {
//        In this operation, you are expected to finds how many distinct elements there are in stack
//        and queue files
        Queue tempQueue1 = copy();
        if (tempQueue1.isEmpty()) {
            return 0;
        }
        else if (tempQueue1.size() == 1) {
            return 1;
        }

        Queue tempQueue2 = new Queue();

        tempQueue1.sortElements();
        int data1 = 0;
        int data2 = tempQueue1.peek();

        while (!tempQueue1.isEmpty()) {
            if (data1 != data2) {
                tempQueue2.enqueue(data2);
            }
            data1 = tempQueue1.peek();
            tempQueue1.dequeue();
            if (!tempQueue1.isEmpty()) {
                data2 = tempQueue1.peek();
            }
        }
        return tempQueue2.size();

    }


    public void sortElements() {

        Queue tempQueue = new Queue();
        Queue copyQueue = copy();
        int totalElements = copyQueue.size();


        int firstNumber = 0;

        while (totalElements > 0) {
            totalElements--;
            for (int i = 0; i < size(); i++) {
                if (copyQueue.isEmpty()) {
                    break;
                }
                if (i == 0) {
                    firstNumber = copyQueue.dequeue();
                }
                int secondNumber = copyQueue.dequeue();
                if (firstNumber <= secondNumber) {
                    int tempNumber = firstNumber;
                    firstNumber = secondNumber;
                    secondNumber = tempNumber;
                }
                tempQueue.enqueue(secondNumber);
            }
            tempQueue.enqueue(firstNumber);

            copyQueue = tempQueue.copy();
            tempQueue.reset();
        }

        reset();
        while (!copyQueue.isEmpty()) {
            enqueue(copyQueue.dequeue());
        }

    }


    public void removeGreater(Integer number) {
//        In this operation, a value is read from the input file then remove all numbers from the queue
//        or stack which are greater than this number. After this operation, the final version of the
//        stack or queue is printed on the their output files

        Queue tempQueue = new Queue();

        int lengthOfQueue = size();
        for (int i = 0; i < lengthOfQueue; i++) {
            Integer tempNumber = dequeue();
            if ( !(tempNumber > number)) {
                tempQueue.enqueue(tempNumber);
            }
        }

        // reset the main stack
        reset();

        int lengthOfTempStack = tempQueue.size();
        for (int i = 0; i < lengthOfTempStack; i++) {
            enqueue(tempQueue.dequeue());
        }
    }

    /**
     * This function removes all elements in the stack with the function pop() and adds
     * all elements to integer arraylist. While removing the elements, it adds values to another stack and reverse it
     * in order to get back to first structure which is given by user.
     * @return distance
     */
    public Integer calculateDistance() {
//        In this operation, finds the sum of the distances of all elements to other all elements in the
//      current queue or stack file. Then, the results are then printed in the specified format in the
//      queueOut.txt file for stack in stackOut.txt.

        int distance = 0;
        Queue tempQueue1 = copy();

        while (!tempQueue1.isEmpty()) {
            int data =  tempQueue1.dequeue();
            Queue tempQueue2 = copy();
            while (!tempQueue2.isEmpty()) {
                int tempData = tempQueue2.dequeue();
                distance += Math.abs(data - tempData);
            }
        }
        return distance/2;
    }


    public void addOrRemove(Integer number) {
//        In this operation, a negative or positive value is read from the input file. If the number is
//        negative, remove elements as the number of times. If the number is positive, add new random
//        elements as the number of times (between 0-50).

        if (number < 0) {
            for (int i = number; i < 0; i++) {
                dequeue();
            }
        }
        else {
            for (int i = number; i > 0; i--) {
                enqueue(ThreadLocalRandom.current().nextInt(1, 51));
            }
        }
    }

    /**
     * Takes the number as a parameter and loops inside the main queue (by pop() method) n(number) times. When reaches the
     * number stops now the n'th element is the front element of the queue. Adds it to another queue and makes same
     * transactions while number greater than zero.
     * @param number
     */
    public void reverse(Integer number) {
        Queue tempQueue1 = copy();
        Queue tempQueue2 = new Queue();
        int mainNumber = number;

        while (number > 0) {
            for (int i = 0; i < number - 1; i++) {
                int x = tempQueue1.dequeue();
                tempQueue1.enqueue(x);
            }

            tempQueue2.enqueue(tempQueue1.peek());
            tempQueue1 = copy();

            number--;

        }

        // remove first n(number) elements from temporary queue
        int numberForRemovingReversedElements = mainNumber;

        while (numberForRemovingReversedElements > 0) {
            numberForRemovingReversedElements--;
            tempQueue1.dequeue();
        }

        //reset the main queue in order to order all elements correctly
        reset();

        while (!tempQueue2.isEmpty()) {
            enqueue(tempQueue2.dequeue());
        }

        while (!tempQueue1.isEmpty()) {
            enqueue(tempQueue1.dequeue());
        }

    }




}
