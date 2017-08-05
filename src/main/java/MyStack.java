import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MyStack {
    private ArrayList<Integer> mStack;
    private HashMap<Integer, Integer> mDeletedValuesMap;
    private PriorityQueue<Integer> minValueHeap;

    public MyStack() {
        mStack = new ArrayList<>();
        mDeletedValuesMap = new HashMap<>();
        minValueHeap = new PriorityQueue<>();
    }

    public void push(int value) {
        mStack.add(value);
        minValueHeap.add(value);
    }

    public int pop() {
        final Integer poppedValue = mStack.remove(mStack.size() - 1);
        addValueIntoDeletedValuesMap(poppedValue);
        return poppedValue;
    }

    private void addValueIntoDeletedValuesMap(Integer poppedValue) {
        if (mDeletedValuesMap.containsKey(poppedValue)) {
            int cnt = mDeletedValuesMap.get(poppedValue);
            mDeletedValuesMap.put(poppedValue, cnt+1);
        } else {
            mDeletedValuesMap.put(poppedValue, 1);
        }
    }

    public int min() {
        int head = minValueHeap.peek();
        while (!mDeletedValuesMap.isEmpty() && mDeletedValuesMap.containsKey(head)) {
            minValueHeap.poll();
            int newCnt = mDeletedValuesMap.get(head) - 1;
            if (newCnt == 0)
                mDeletedValuesMap.remove(head);
            else
                mDeletedValuesMap.put(head, newCnt);

            head = minValueHeap.peek();
        }

        return head;
    }
}