Nama: Evelyn Paramesti Hotmauli Silalahi <br>
NPM: 2206031012 <br>
Kelas: C <br>

<details>
<summary>Tutorial 1</summary>

# Tutorial-1

### Reflection 1

Pada tutorial kali ini, beberapa prinsip coding standard yang sudah diterapkan antara lain clean code, git flow, dan testing. Prinsip clean code diterapkan dengan menggunakan penamaan variabel, function, dan class yang sesuai dengan fungsinya. Hal ini dilakukan agar nama-nama tersebut lebih mudah diingat dan dipahami. Alur pengerjaan tutorial juga dibantu dengan git flow, tiap fitur dikerjakan di branch git yang berbeda. Untuk menguji jalannya program, diterapkan unit testing dan functional testing.

Namun, beberapa prinsip seperti secure coding dan error handling belum diterapkan sepenuhnya. Program masih bisa dikembangkan dengan menambahkan validasi tipe data input. Selain itu, masih ada beberapa kasus yang kemungkinan menyebabkan error yang belum dihandle, seperti input nama kosong atau input dengan _hazardous character_.

### Reflection 2

1. Setelah membuat unit test, saya merasa bahwa kali ini saya dituntut untuk lebih memperhatikan detail atau bagian-bagian kecil pada program. Untuk suatu class, banyaknya unit test yang dibutuhkan tergantung dari banyaknya fungsi pada class tersebut. Untuk memferivikasi program, code coverage yang dibutuhkan adalah sekitar di atas 80%. Tetapi, code coverage tidak menjamin kesempurnaan program. Bahkan 100% code coverage pun belum tentu berarti program telah sempurna, karena bisa saja masih ada _unexpected scenario_ yang tidak disertakan saat testing.

2. Penambahan class tersebut akan mengurangi _cleanliness_ dari kode program karena terdapat duplikasi kode dengan set up yang tidak berbeda jauh sehingga kode menjadi kurang efisien. Fungsi pemeriksaan kuantitas produk dapat ditambahkan pada class yang sama dengan fungsi untuk create product, tidak perlu membuat class baru.

</details>

<details>
<summary>Tutorial 2</summary>

# Tutorial-2

### Reflection 1
Code quality issue: 
- *Unnecessary public modifier*. Terdapat method pada interface yang menggunakan modifier `public`. Penggunaan modifier ini dianggap _redundant_ karena secara interface memang bersifat public dan static secara implisit. Penggunaan modifier public tidak diperlukan untuk method interface sehingga saya mengubah modifier method tersebut menjadi default.

- *Only one method invocation is expected when testing runtime exceptions*
  Terdapat method pada unit test yang memeriksa runtime exception menggunakan lambda. Dalam method tersebut, ada kemungkinan terjadinya dua runtime exception. Akan lebih baik apabila lambda tersebut hanya menerima satu kemungkinan exception. Maka, saya mengubah susunan kodenya agar method ini hanya menerima satu kemungkinan exception.

- *Field dependency injection should be avoided*
  Terdapat kode instansiasi objek dengan injeksi dependensi @Autowired. Injeksi ini tidak disarankan karena membuat objek yang diinstansiasi memiliki state yang tidak valid dan proses testing akan menjadi cenderung lebih sulit. Dependensi pada objek tersebut akan menjadi tidak eksplisit ketika di-inject. Maka, saya menghapus injeksi @Autowired pada instansiasi objek tersebut sehingga instansiasi objek dapat dilakukan secara langsung.

### Reflection 2
Menurut saya, implementasi CI/CD pada github workflow di tutorial kali ini sudah memenuhi definisi Continuous Integration dan Continuous Deployment. Terdapat workflow pada `ci.yml` yang akan otomatis menjalankan unit tests menggunakan gradle sebagai tool setiap kali terdapat perubahan source code yang di-push ke repository. Workflow ini merupakan penerapan Continuous Integration. Selain itu, terdapat juga Dockerfile yang berisi workflow untuk secara otomatis melakukan deployment setiap kali terdapat push atau pull request pada branch utama. Workflow ini merupakan penerapan Continous Deployment. Terdapat pula workflow `pmd.yml` yang akan otomatis melakukan code scanning setiap kali ada event push di branch manapun. Otomatisasi dengan menggunakan script workflow ini merupakan implementasi CI/CD pada tutorial kali ini.  

</details>

# Tutorial-3

1. Explain what principles you apply to your project!

- SRP: prinsip ini menyatakan bahwa sebuah class hanya memiliki satu tanggungjawab. Pada tutorial ini, prinsip ini diterapkan dengan memisahkan `CarController` dan `ProductController` ke dalam dua file yang berbeda, kemudian menghilangkan inheritance dari `CarController` terhadap `ProductController`. Hal ini dilakukan agar setiap class fokus pada tanggungjawabnya masing-masing.
- OCP: prinsip ini menyatakan bahwa penambahan fitur atau behavior suatu modul dapat dilakukan tanpa harus memodifikasi kode yang sudah ada. Pada tutorial ini, apabila kita ingin menambahkan tipe model baru seperti misalnya `Motorcycle`, kita dapat menambahkan controller, repository, dan service baru untuk `Motorcycle` tanpa harus mengubah kode untuk `Car` dan `Product` yang sudah ada.
- LSP: prinsip ini menyatakan bahwa sebuah objek dari suatu subclass harus bisa menerapkan fungsionalitas yang sama dengan superclass nya. Pada tutorial kali ini, saya tidak menerapkan inheritance sehingga prinsip ini tidak perlu digunakan.
- ISP: prinsip ini menyatakan bahwa interface dibuat spesifik agar class yang ada hanya menerapkan interface yang diperlukan. Pada tutorial ini, interface service untuk car dan product dipisah dengan adanya `CarService.java` dan `ProductService.java`. Pemisahan ini dilakukan agar `CarServiceImpl` dapat meng-override method yang diperlukan/relevan saya, yaitu method pada `CarService`. `CarServiceImpl` tidak perlu mengimplementasikan `ProductService` yang tidak relevan dengan keperluannya terkait objek car.
- DIP: prinsip ini menyatakan bahwa _high-level modules_ sebaiknya tidak bergantung secara langsung terhadap _low-level modules_, dan keduanya seharusnya bergantung pada abstraksi. Pada tutorial ini, prinsip ini diterapkan dengan membuat class `CarController` bergantung pada interface `CarService`, bukan bergantung pada class `CarServiceImpl` secara langsung.

2. Explain the advantages of applying SOLID principles to your project with examples.
- Dengan SRP, kode akan lebih mudah untuk di-_maintain_ dan pencarian bug akan cenderung lebih mudah karena kode sudah terpisah menjadi bagian-bagian yang spesifik sesuai fungsinya masing-masing. Contohnya, pemisahan antara `CarController` dan `ProductController` akan memudahkan pencarian error apabila terdapat error pada salah satu controller.
- Dengan ISP, implementasi suatu interface akan lebih efisien karena suatu class hanya perlu meng-override method dari interface yang relevan saja. Contohnya adalah pemisahan `CarService` dan `ProductService` agar `CarServiceImpl` dapat meng-override method yang diperlukan/relevan saya, yaitu method pada `CarService` dan tidak perlu mengimplementasikan `ProductService` yang tidak relevan dengan keperluannya terkait objek car.
- Dengan DIP, kode akan menjadi lebih fleksibel karena abstraksi tidak bergantung pada detail. Contohnya, `CarController` bergantung pada `CarService` dan bukan bergantung pada `CarServiceImpl` secara langsung.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.
- Tanpa SRP, kode akan cenderung lebih susah untuk di-_maintain_, pencarian error atau bug akan cenderung lebih sulit juga karena penempatan kode yang kurang spesifik. Contohnya, jika `CarController` digabung dengan `ProductController`, pencarian error pada salah satunya akan lebih sulit.
- Tanpa ISP, ada kemungkinan bahwa suatu class perlu meng-override method yang tidak relevan dari interface yang di-implement. Contohnya, jika `CarService` digabung dengan `ProductService`, maka `CarServiceImpl` juga perlu meng-override method-method pada `ProductService` yang tidak relevan dengan car.
- Tanpa DIP, suatu class akan terlalu bergantung pada implementasi konkrit dari class lainnya sehingga kode kurang fleksibel. Contohnya, jika `CarController` bergantung langsung pada `CarServiceImpl` dan bukan `CarService`, maka modifikasi atau perbaikan kode pada `CarController` dan `CarServiceImpl` menjadi kurang efisien.
