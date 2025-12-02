# SmartLibrary – Kütüphane Yönetim Sistemi

**Ders:** Nesneye Dayalı Programlama II
**Hazırlayan:** Beyza Çevik
**Öğrenci No:** 20230108010



## Projenin Genel Amacı

SmartLibrary, Java ile geliştirilmiş, SQLite veritabanı kullanan basit bir kütüphane yönetim sistemidir.
Projenin hedefi; nesneye dayalı programlama prensiplerini kullanarak kitap, öğrenci ve ödünç işlemlerinin yönetilebildiği, konsol tabanlı bir uygulama oluşturmaktır.

Bu sistemde kullanıcı yeni kitaplar ve öğrenciler ekleyebilir, ekli kayıtları listeleyebilir, kitap ödünç verme ve geri alma işlemlerini gerçekleştirebilir.



## Kullanılan Teknolojiler

* Java (Nesneye Dayalı Programlama)
* SQLite
* JDBC
* PreparedStatement
* ArrayList
* IntelliJ IDEA



## Sınıf Yapısı

### 1. **Temel Varlık Sınıfı**

* **BaseEntity**

  * Tüm varlıkların ortak `id` alanı burada tutulur.

### 2. **Kitap – Book**

* id
* title
* author
* year

### 3. **Öğrenci – Student**

* id
* name
* department

### 4. **Ödünç İşlemi – Loan**

* id
* bookId
* studentId
* dateBorrowed
* dateReturned

### 5. **Database**

* SQLite bağlantısını kurar
* Tabloları oluşturur
* Bağlantının çalışmasını kontrol eder



## Repository Yapısı

Her tablo için ayrı Repository sınıfları bulunmaktadır.
Bu sınıflar, ilgili tabloda CRUD işlemlerinin yapılmasını sağlar.

* **BookRepository**
* **StudentRepository**
* **LoanRepository**

Bu sınıflarda bulunan ortak metotlar:

* `add()`
* `update()`
* `delete()`
* `getById()`
* `getAll()`

LoanRepository ek olarak bir kitabın ödünçte olup olmadığını kontrol eden metot içerir.



## Veri Tabanı Yapısı

### books

| id | title | author | year |

### students

| id | name | department |

### loans

| id | bookId | studentId | dateBorrowed | dateReturned |

Tablolar uygulama çalışmaya başladığında otomatik olarak oluşturulur.



## Uygulama Menüsü

Konsol ekranı üzerinden kullanıcıya şu işlemler sunulur:

1. Kitap ekle
2. Kitapları listele
3. Öğrenci ekle
4. Öğrencileri listele
5. Kitap ödünç ver
6. Ödünç kayıtlarını görüntüle
7. Kitap geri teslim al
8. Çıkış

Ödünç listeleme ekranında **kitap adı** ve **öğrenci adı** ilişkisel olarak görüntülenir.



## Kurulum

1. Projeyi bilgisayarınıza indirin.
2. `sqlite-jdbc` sürücüsünü `lib` klasörüne ekleyin.
3. IntelliJ IDEA → Project Structure → Dependencies kısmından JAR dosyasını projeye tanıtın.
4. Uygulamayı `Main.java` üzerinden çalıştırın.
5. Veritabanı dosyası (`smartlibrary.db`) otomatik olarak oluşturulur.



## Sonuç

SmartLibrary, ders kapsamında istenen tüm gereksinimleri karşılayan; sınıf yapıları, veritabanı bağlantısı, JDBC işlemleri ve konsol menüsüyle çalışır bir kütüphane otomasyonudur.
Öğrenci, kitap ve ödünç süreçlerinin tamamı nesneye dayalı programlama prensiplerine uygun şekilde yapılandırılmıştır.
