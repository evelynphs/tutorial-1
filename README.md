# Tutorial-1

Nama: Evelyn Paramesti Hotmauli Silalahi <br>
NPM: 2206031012 <br>
Kelas: C <br> 

### Reflection 1

Pada tutorial kali ini, beberapa prinsip coding standard yang sudah diterapkan antara lain clean code, git flow, dan testing. Prinsip clean code diterapkan dengan menggunakan penamaan variabel, function, dan class yang sesuai dengan fungsinya. Hal ini dilakukan agar nama-nama tersebut lebih mudah diingat dan dipahami. Alur pengerjaan tutorial juga dibantu dengan git flow, tiap fitur dikerjakan di branch git yang berbeda. Untuk menguji jalannya program, diterapkan unit testing dan functional testing.

Namun, beberapa prinsip seperti secure coding dan error handling belum diterapkan sepenuhnya. Program masih bisa dikembangkan dengan menambahkan validasi tipe data input. Selain itu, masih ada beberapa kasus yang kemungkinan menyebabkan error yang belum dihandle, seperti input nama kosong atau input dengan _hazardous character_.

### Reflection 2

1. Setelah membuat unit test, saya merasa bahwa kali ini saya dituntut untuk lebih memperhatikan detail atau bagian-bagian kecil pada program. Untuk suatu class, banyaknya unit test yang dibutuhkan tergantung dari banyaknya fungsi pada class tersebut. Untuk memferivikasi program, code coverage yang dibutuhkan adalah sekitar di atas 80%. Tetapi, code coverage tidak menjamin kesempurnaan program. Bahkan 100% code coverage pun belum tentu berarti program telah sempurna, karena bisa saja masih ada _unexpected scenario_ yang tidak disertakan saat testing.

2. Penambahan class tersebut akan mengurangi _cleanliness_ dari kode program karena terdapat duplikasi kode dengan set up yang tidak berbeda jauh sehingga kode menjadi kurang efisien. Fungsi pemeriksaan kuantitas produk dapat ditambahkan pada class yang sama dengan fungsi untuk create product, tidak perlu membuat class baru.