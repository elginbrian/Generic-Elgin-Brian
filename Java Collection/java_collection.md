# Java Collections

**Java Collections** <br/>
Collection adalah sebuah framework yang dibuat untuk menyimpan dan memanipulasi sebuah objek. Collection biasanya digunakan pada sebuah data seperti mencari, meng-urutkan, meng-input dan menghapus.

**Jenis-Jenis Koleksi**

<li> List: Menyimpan elemen dalam urutan tertentu. Contoh class: ArrayList, LinkedList, Vector. <br/>
<li> Set: Tidak memperbolehkan duplikasi elemen. Contoh class: HashSet, LinkedHashSet, TreeSet. <br/>
<li> Map: Setiap kunci unik dipetakan ke nilai tertentu. Contoh class: HashMap, LinkedHashMap, TreeMap. <br/>
<li> Queue: Mengikuti prinsip FIFO (First-In-First-Out). Contoh class: PriorityQueue, LinkedList. <br/>
<br/>

**Kegunaan** <br/>

<li> Iterasi: Memungkinkan iterasi elemen menggunakan iterator.
Penambahan dan Penghapusan: Memungkinkan untuk menambah dan menghapus elemen. <br/>
<li> Mengelola elemen: Seperti memeriksa ada tidaknya elemen, mendapatkan elemen, atau mengubah elemen. <br/>
<li> Pengurutan: Beberapa collection menyediakan kemampuan untuk mengurutkan elemen. <br/>

<br/>

**Cara Menggunakan** <br/>
Membuat Objek Koleksi: Gunakan konstruktor untuk membuat objek koleksi. Misalnya,

```java
ArrayList<String> list = new ArrayList<>();
```

Menambahkan Elemen: Gunakan metode add() untuk menambahkan elemen ke koleksi.
Menghapus Elemen: Gunakan metode remove() untuk menghapus elemen dari koleksi.
Mengakses Elemen: Gunakan metode get() untuk mengakses elemen di indeks tertentu. <br/>

**Contoh Penggunaan** <br/>

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Membuat objek ArrayList
        ArrayList<String> list = new ArrayList<>();

        // Menambahkan elemen
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Iterasi melalui elemen
        for (String fruit : list) {
            System.out.println(fruit);
        }
    }
}
```
