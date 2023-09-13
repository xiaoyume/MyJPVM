package org.jpvm;

import java.util.Iterator;

public class IterClass implements Iterable<Integer>{

    private Integer[] els;

    public IterClass(Integer[] els) {
        this.els = els;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Miterator();
    }

    private class Miterator implements Iterator<Integer>{
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < els.length;
        }

        @Override
        public Integer next() {
            return els[index ++];
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {1,2,3,4,45,65,45,3,2,32};
        IterClass aClass = new IterClass(nums);
        Miterator itr = (Miterator) aClass.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }

}
