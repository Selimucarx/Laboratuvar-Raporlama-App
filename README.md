# Laboratuvar Raporlama Uygulaması 

Laboratuvar raporlama uygulamasının kurulum ve kullanımı ile ilgili gereksinimler, proje detayları ve adım adım kurulum rehberini içermektedir.

## Gereksinimler

Aşağıdaki araçların kurulu olması gerekmektedir:

- [MAVEN](https://maven.apache.org/download.cgi)
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [NPM](https://nodejs.org/en/download)

## Proje Detayları

Laboratuvar raporlama uygulamamız, kod standartlarına uygun CRUD işlemleri içermekte ve `Patient`, `Laborant`, `Reports`, `User`, `ROLES` tablolarını yönetmektedir. Uygulama, yetkilendirme ve güvenlik işlemleri için aşağıdaki teknoloji ve yöntemleri kullanmaktadır:

### Gerekçeler

1. **Java 17:** Spring Boot 3+ versiyonunun kullanılması amacıyla Java 17 ve üstü bir versiyon gerekmektedir. Bu sayede, `record` sınıflarından yararlanılmaktadır.
2. **Spring Boot 3.2.2:** `@GeneratedValue` ile UUID kullanımının kolaylığı, güncel Spring Boot sürümleri ile Security, JPA gibi kütüphanelerin güncel versiyonlarını kullanabilme imkanı sağlamaktadır.
3. **H2 Database:** Projenin hızlı ve kolay erişilebilir olması için tercih edilmiştir.
4. **MapStruct:** Performans ve temiz kod sağlama avantajları.
5. **JWT:** Gerçekçi yetkilendirme ve doğrulama işlemleri için.
6. **UserDetails:** Kullanıcı yönetimini kolaylaştırmak amacıyla.
7. **BCryptPasswordEncoder:** Veritabanında şifre güvenliğini artırmak için.
8. **JpaSpecificationExecutor:** JPA sorguları ile yeterli esneklik sağlamadığında ve yeni bir yöntem denemek istendiğinde kullanılmaktadır.

### Sayfalar

- `/register`: USER veya ADMIN rolünde yeni kullanıcı oluşturabileceğiniz sayfa.
- `/login`: Yalnızca veritabanında USER veya ADMIN rolünde kaydı bulunan kişilerin giriş yapabileceği sayfa.
- `/patient`: USER veya ADMIN yetkisine sahip kullanıcıların ekleme, listeleme, silme, filtreleme işlemleri yapabileceği sayfa.
- `/laborant`: USER veya USER yetkisine sahip kullanıcıların ekleme, listeleme, silme, filtreleme işlemleri yapabileceği sayfa.
- `/report`: Patient ve laborant için ek olarak güncelleme işleminin bulunduğu ve yalnızca ADMIN yetkisine sahip kullanıcının silebileceği rapor verisine sahip sayfa.

## Backend'i Çalıştırmak İçin Gerekli Adımlar

Backend kök dizininde terminali açıp sırasıyla aşağıdaki komutları giriniz:

```
mvn clean install
mvn spring-boot:run
```
Artik Backend ayaga kalkmis durumda.

---
### **Frontend calistirmak icin gerekli adimlar**

2. Frontend kok dizinde terminali acip sirasiyla komutlari giriyorsunuz.
```
npm install

npm start

```
Artik Frontend ayaga kalkmis durumda.

---
### **H2 Database ile verileri kontrol edebilmek icin.**

[H2 Database](http://localhost:8080/h2-console/ ) adresi ile database verilerinizi gorebilirsiniz. Assagidaki bilgileri H2 Database Konsoluna giriniz.

```
JDBC URL: jdbc:h2:mem:LabReportingDB

User Name:sa

Password:	
```

---
Bu adımı uygulamanıza rağmen projeyi çalıştıramadıysanız benimle iletişim kurabilirsiniz.

	selim.ucarx@gmail.com 
