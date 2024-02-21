package org.esni.notion.jdbc.adpter;


import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerator;

import java.util.ArrayList;

public class NotionEnumerable<E> extends AbstractEnumerable<E> {

    private final ArrayList<E> datas;

    public NotionEnumerable(ArrayList<E> datas) {
        this.datas = datas;
    }

    @Override
    public Enumerator<E> enumerator() {
        return new NotionEnumerator<>(this.datas);
    }


    class NotionEnumerator <E> implements Enumerator<E> {

        private final ArrayList<E> datas;
        private final int size;
        private int current_index;

        public NotionEnumerator(ArrayList<E> datas) {
            this.datas = datas;
            this.size = datas.size();
            this.current_index = -1;
        }

        @Override
        public E current() {
            return this.datas.get(this.current_index);
        }

        @Override
        public boolean moveNext() {
            return ++current_index < this.size;
        }

        @Override
        public void reset() {
            this.current_index = 0;
        }

        @Override
        public void close() {
        }

    }

}


