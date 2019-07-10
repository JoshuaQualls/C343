import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {
    ArrayList<Item<Integer>> i1 = new ArrayList<>();
    ArrayList<Item<Integer>> i2 = new ArrayList<>();
    ArrayList<Item<Integer>> i3 = new ArrayList<>();

    @Test
    public void isEmpty(){
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        BinaryHeap<Integer> th2 = new BinaryHeap<>();
        i2.add(new Item<Integer>(1, "1", 1));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);

        assertFalse(th1.isEmpty());
        assertTrue(th2.isEmpty());
        assertFalse(th3.isEmpty());

    }

    @Test
    public void getSize(){
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }
        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        BinaryHeap<Integer> th2 = new BinaryHeap<>();
        i2.add(new Item<Integer>(1, "1", 1));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);


        assertEquals(400, th1.getSize());
        assertEquals(0, th2.getSize());
        assertEquals(1, th3.getSize());
        assertEquals(100, th4.getSize());
    }

    @Test
    public void findMin(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }
        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        Item<Integer> testItem = new Item<>(1, "test", 1);
        Item<Integer> testItem2 = new Item<>(5, "test2", 5);

        assertEquals(testItem, th1.findMin());
        assertEquals(testItem, th3.findMin());
        assertEquals(testItem2, th4.findMin());
    }

    @Test
    public void getElems(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }
        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        assertEquals(th1.getElems(), i1);
        assertEquals(th3.getElems(), i2 );
        assertEquals(th4.getElems(), i3);
    }

    @Test
    public void getParentIndex()throws NoParentE{
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        assertEquals(5, th1.getParentIndex(10));
        assertEquals(45, th4.getParentIndex(90));
        assertEquals(1, th3.getParentIndex(3));
    }

    @Test
    public void getLeftChildIndex()throws NoLeftChildE{
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        assertEquals(40, th1.getLeftChildIndex(20));
        assertEquals(90, th1.getLeftChildIndex(45));
        assertEquals(20, th4.getLeftChildIndex(10));
    }

    @Test
    public void getRightChildIndex() throws NoRightChildE{
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        assertEquals( 31,th1.getRightChildIndex(15));
        assertEquals(101,th1.getRightChildIndex(50));
        assertEquals(19, th4.getRightChildIndex(9));
    }

    @Test
    public void swap(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        i2 = new ArrayList<>();
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> testHeap5 = new BinaryHeap<>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        th1.swap(1, 2);
        th3.swap(1, 2);
        th4.swap(1, 2);
        assertEquals(th1.getElems().get(1), i1.get(2));
        assertEquals(th3.getElems().get(1), i2.get(2));
        assertEquals(th4.getElems().get(1), i3.get(2));
    }

    @Test
    public void getKey(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        assertEquals(1, th1.getKey(10));
        assertEquals(1, th3.getKey(1));
        assertEquals(10,th4.getKey(15));
    }

    @Test
    public void updateKey(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        th1.updateKey(1, 10);
        th3.updateKey(2, 20);
        th4.updateKey(3, 40);
        assertEquals(10,th1.getKey(1));
        assertEquals(20,th3.getKey(2));
        assertEquals(40,th4.getKey(3) );
    }

    @Test
    public void moveUp(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        th1.moveUp(1);
        th3.moveUp(2);
        th4.moveUp(5);
        assertEquals(1,th1.getKey(1));
        assertEquals(2,th3.getKey(2));
        assertEquals(8,th4.getKey(5));
    }

    @Test
    public void insert() {
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        th1.insert(i2.get(1));
        th3.insert(i2.get(2));
        th4.insert(i2.get(0));
        assertEquals(th1.getElems().get(1), i2.get(1));
        assertEquals(th3.getElems().get(2), i2.get(2));
        assertEquals(th4.getElems().get(0), i2.get(0));
    }

    @Test
    public void minChildIndex() throws NoLeftChildE{
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        assertEquals(3, th1.minChildIndex(1));
        assertEquals(20, th1.minChildIndex(10));
        assertEquals(11, th4.minChildIndex(5));
        assertEquals(161, th1.minChildIndex(80));
    }

    @Test
    public void moveDown(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        th1.moveDown(1);
        th3.moveDown(2);
        th4.moveDown(5);
        assertEquals(th1.getKey(1), 1);
        assertEquals(th3.getKey(2), 2);
        assertEquals(th4.getKey(5), 8);
    }

    @Test
    public void extractMin(){
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                i1.add(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                i3.add(new Item<>(i + j, "test2", j));
            }
        }

        BinaryHeap<Integer> th1 = new BinaryHeap<>(i1);
        i2.add(new Item<Integer>(1, "test", 1));
        i2.add(new Item<Integer>(2, "test", 2));
        i2.add(new Item<Integer>(3, "test", 3));
        BinaryHeap<Integer> th3 = new BinaryHeap<Integer>(i2);
        BinaryHeap<Integer> th4 = new BinaryHeap<>(i3);

        i1.remove(0);
        assertEquals(th1.extractMin(), i1.get(0));
        assertEquals(th3.extractMin(), i2.get(0));
        assertEquals(th4.extractMin(), i3.get(0));
    }

    // D-Heap

    @Test
    public void isDEmpty(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        DHeap<Integer> dHeapEmpty = new DHeap<>(2);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertFalse(dhTest.isEmpty());
        assertTrue(dHeapEmpty.isEmpty());
        assertFalse(dhTest2.isEmpty());
    }

    @Test
    public void getDSize(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertEquals(400, dhTest.getSize());
        assertEquals(dhTest2.getSize(), 100);
        assertEquals(dhTest3.getSize(), 900);
    }

    @Test
    public void findDMin(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        Item<Integer> testItem = new Item<>(1, "test", 1);
        Item<Integer> testItem2 = new Item<>(5, "test2", 5);

        assertEquals(dhTest.findMin(), testItem);
        assertEquals(dhTest2.findMin(), testItem2);
        assertEquals(dhTest3.findMin(), testItem);
    }

    @Test
    public void getDElems(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        ArrayList<Item<Integer>> arr1 = new ArrayList<>();
        ArrayList<Item<Integer>> arr2 = new ArrayList<>();
        ArrayList<Item<Integer>> arr3 = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                arr1.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                arr2.add(new Item<>(i + j, "test2", j));
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                arr3.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertEquals(dhTest.getElems(), arr1);
        assertEquals(dhTest2.getElems(), arr2);
        assertEquals(dhTest3.getElems(), arr3);
    }

    @Test
    public void getDParentIndex()throws NoParentE{
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertEquals(dhTest.getParentIndex(10), 3);
        assertEquals(dhTest2.getParentIndex(20), 5);
        assertEquals(dhTest3.getParentIndex(25), 5);
    }

    @Test
    public void getDChildrenIndex() throws NoChildE{
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertEquals(dhTest.getChildIndex(1, 2), 3);
        assertEquals(dhTest2.getChildIndex(2, 3), 8);
        assertEquals(dhTest3.getChildIndex(10, 11), 57);
    }

    @Test
    public void swapD(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        ArrayList<Item<Integer>> arr1 = new ArrayList<>();
        ArrayList<Item<Integer>> arr2 = new ArrayList<>();
        ArrayList<Item<Integer>> arr3 = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                arr1.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                arr2.add(new Item<>(i + j, "test2", j));
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                arr3.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        dhTest.swap(1, 2);
        dhTest2.swap(3, 4);
        dhTest3.swap(10, 12);
        assertEquals(arr1.get(2), dhTest.getElems().get(1));
        assertEquals(arr2.get(4), dhTest2.getElems().get(3));
        assertEquals(arr3.get(12), dhTest3.getElems().get(10));
    }

    @Test
    public void getDKey(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 25; i++) {
            for (int j = 25; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertEquals(dhTest.getKey(5), 1);
        assertEquals(dhTest2.getKey(10), 9);
        assertEquals(dhTest3.getKey(120), 2);
    }

    @Test
    public void updateDKey(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        dhTest.updateKey(1, 20);
        dhTest2.updateKey(3, 40);
        dhTest3.updateKey(4, 50);
        assertEquals(dhTest.getKey(1), 20);
        assertEquals(dhTest2.getKey(3), 40);
        assertEquals(dhTest3.getKey(4), 50);
    }

    @Test
    public void moveDUp(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        dhTest.moveUp(1);
        dhTest2.moveUp(10);
        dhTest3.moveUp(11);
        assertEquals(dhTest.getKey(1), 1);
        assertEquals(dhTest2.getKey(10), 9);
        assertEquals(dhTest3.getKey(11), 1);
    }

    @Test
    public void insertD(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        ArrayList<Item<Integer>> arr1 = new ArrayList<>();
        ArrayList<Item<Integer>> arr2 = new ArrayList<>();
        ArrayList<Item<Integer>> arr3 = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                arr1.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                arr2.add(new Item<>(i + j, "test2", j));
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                arr3.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        dhTest.insert(arr1.get(1));
        dhTest2.insert(arr2.get(2));
        dhTest3.insert(arr3.get(3));
        assertEquals(arr1.get(1), dhTest.getElems().get(1));
        assertEquals(arr2.get(2), dhTest2.getElems().get(2));
        assertEquals(arr3.get(3), dhTest3.getElems().get(3));
    }

    @Test
    public void minDChildrenIndex()throws NoChildE{
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        assertEquals(dhTest.minChildIndex(2), 5 );
        assertEquals(dhTest2.minChildIndex(4), 15);
        assertEquals(dhTest3.minChildIndex(16), 78 );
    }

    @Test
    public void moveDDown(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 5; i < 10; i++){
            for (int j = 10; j > 6; j --){
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }

        dhTest.moveDown(1);
        dhTest2.moveDown(15);
        dhTest3.moveDown(20);
        assertEquals(dhTest.getKey(1), 1);
        assertEquals(dhTest2.getKey(15), 8);
        assertEquals(dhTest3.getKey(20), 5);
    }

    @Test
    public void extractDMin(){
        DHeap<Integer> dhTest = new DHeap<>(3);
        DHeap<Integer> dhTest2 = new DHeap<>(4);
        DHeap<Integer> dhTest3 = new DHeap<>(5);
        ArrayList<Item<Integer>> arr1 = new ArrayList<>();
        ArrayList<Item<Integer>> arr2 = new ArrayList<>();
        ArrayList<Item<Integer>> arr3 = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            for (int j = 20; j > 0; j--) {
                arr1.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j --){
                arr2.add(new Item<>(i + j, "test2", j));
                dhTest2.insert(new Item<>(i + j, "test2", j));
            }
        }
        for(int i = 0; i < 30; i++) {
            for (int j = 30; j > 0; j--) {
                arr3.add(new Item<Integer>(i+j, "test",
                        j));
                dhTest3.insert(new Item<Integer>(i+j, "test",
                        j));
            }
        }
        arr1.remove(0);
        arr2.remove(0);
        arr3.remove(0);
        assertEquals(dhTest.extractMin(), arr1.get(0));
        assertEquals(dhTest2.extractMin(), arr2.get(0));
        assertEquals(dhTest3.extractMin(), arr3.get(0));
    }
}
