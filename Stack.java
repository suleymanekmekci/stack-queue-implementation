import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Stack {
    private ArrayList<Integer> stack = new ArrayList<>();
    int top = 0;



    public void reset() {
        stack = new ArrayList<>();
        top = 0;
    }

    @Override
    public String toString() {
        String string = "";

        Stack tempStack = copy();

        for (int i = top-1; i >= 0; i--) {
            string += tempStack.pop() + " ";
//            string += stack.get(i) + " ";
        }
        return string;
    }



    public Integer size() {
        return top;
    }

    public Integer pop() {
        top--;
        return stack.remove(top);

    }

    public Integer peek() {
        if (!copy().isEmpty()) {
            Stack tempStack = copy();
            int data = tempStack.pop();
            return data;
        }
        else {
            return 0;
        }
    }


    public void push(Integer number) {
        stack.add(top,number);
        top++;
    }

    public boolean isEmpty() {
        return top<=0;
    }

    public void sortElements() {


        Stack tempStack = new Stack();

        int tempNumber= 0;
        while (!isEmpty()) {
            tempNumber = pop();
            while(!tempStack.isEmpty() && tempStack.peek() < tempNumber) {
                push(tempStack.pop());
            }
            tempStack.push(tempNumber);
        }


        int lengthOfTempStack = tempStack.size();
        for (int i = 0; i < lengthOfTempStack; i++) {
            push(tempStack.pop());
        }
        reverse(size());
    }


    public void addOrRemove(Integer number) {
//        In this operation, a negative or positive value is read from the input file. If the number is
//        negative, remove elements as the number of times. If the number is positive, add new random
//        elements as the number of times (between 0-50).

        if (number < 0) {
            for (int i = number; i < 0; i++) {
                pop();
            }
        }
        else {
            for (int i = number; i > 0; i--) {
                push(ThreadLocalRandom.current().nextInt(1, 51));
            }
        }

    }

    public void reverse(Integer number) {
        Stack tempStack1 = new Stack();
        Stack tempStack2 = new Stack();



        while (number > 0) {
            int data = pop();
            tempStack1.push(data);
            number--;

        }

        while (!tempStack1.isEmpty()) {
            tempStack2.push(tempStack1.pop());
        }

        while (!tempStack2.isEmpty()) {
            push(tempStack2.pop());
        }

    }


    public void removeGreater(Integer number) {
//        In this operation, a value is read from the input file then remove all numbers from the queue
//        or stack which are greater than this number. After this operation, the final version of the
//        stack or queue is printed on the their output files

        Stack tempStack = new Stack();

        int lengthOfStack = size();
        for (int i = 0; i < lengthOfStack; i++) {
            Integer tempNumber = pop();
            if ( !(tempNumber > number)) {
                tempStack.push(tempNumber);
            }
        }

        // reset the main stack
        reset();

        int lengthOfTempStack = tempStack.size();
        for (int i = 0; i < lengthOfTempStack; i++) {
            push(tempStack.pop());
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
        Stack tempStack1 = copy();
        while (!tempStack1.isEmpty()) {
            int data =  tempStack1.pop();
            Stack tempStack2 = copy();
            while (!tempStack2.isEmpty()) {
                int tempData = tempStack2.pop();
                distance += Math.abs(data - tempData);
            }
        }
        return distance/2;
    }


    public Stack copy() {
        Stack reversedMainStack = new Stack();

        Stack returnStack = new Stack();

        int tempNumber = top;
        while (tempNumber > 0) {
            int data = pop();
            tempNumber--;
            reversedMainStack.push(data);

        }
        while (reversedMainStack.top> 0) {
            int data = reversedMainStack.pop();
            push(data);
            returnStack.push(data);
        }



        return returnStack;
    }

    public Integer distinctElements() {
//        In this operation, you are expected to finds how many distinct elements there are in stack
//        and queue files

        Stack tempStack1 = copy();
        if (tempStack1.isEmpty()) {
            return 0;
        }
        else if (tempStack1.size() == 1) {
            return 1;
        }

        Stack tempStack2 = new Stack();
//        Stack tempStack2 = copy();
        tempStack1.sortElements();

        int numberOfDistinctElements = 0;

        while (!tempStack1.isEmpty()) {
            int data = tempStack1.pop();
            if (tempStack2.peek() != data) {
                numberOfDistinctElements++;
                tempStack2.push(data);
            }
        }

        return numberOfDistinctElements;

    }


}
