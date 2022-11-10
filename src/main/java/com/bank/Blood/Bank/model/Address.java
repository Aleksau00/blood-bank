package com.bank.Blood.Bank.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Address {

    /*
     * Svaki entitet ima svoj kljuc (surogat kljuc), dok se strategija generisanja
     * kljuceva moze eksplicitno podesiti: - AUTO - generisanje kljuceva se oslanja
     * na perzistencionog provajdera da izabere nacin generisanja (ako je u pitanju
     * Hibernate, selektuje tip na osnovu dijalekta baze, za najpopularnije baze
     * izabrace IDENTITY) - IDENTITY - inkrementalno generisanje kljuceva pri svakom
     * novom insertu u bazu - SEQUENCE - koriste se specijalni objekti baze da se
     * generisu id-evi - TABLE - postoji posebna tabela koja vodi racuna o
     * kljucevima svake tabele Vise informacija na:
     * https://en.wikibooks.org/wiki/Java_Persistence/Identity_and_Sequencing
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * Kolona moze imati ime koje se razlikuje od naziva atributa.
     */
    @Column(name = "country", nullable = false)
    private String country;

    /*
     * Anotacija @Column oznacava da ce neki atribut biti kolona u tabeli
     */
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    /*
     * Primer bidirekcione veze 1:n. Student sadrzi kolekciju ispita, ispit pripada
     * jednom student. Jedna strana veze je anotirana sa @OneToMany, dok je druga
     * anotirana sa @ManyToOne. Dodatno je iskoriscen atribut mappedBy da se naznaci
     * ko je vlasnik veze (student). U bazi ce se u tabeli Exam kreirati dodatna
     * kolona koja ce sadrzati id objekata tipa Student kao strani kljuc. Ako se
     * izostavi mappedBy kreirace se medjutabela koja ce sadrzati 2 kolone - id
     * studenta i id ispita
     *
     * Atributom fetch moze se podesavati nacin dobavljanja povezanih entiteta.
     * Opcije su EAGER i LAZY. FetchType odlucuje da li ce se ucitati i sve veze sa
     * odgovarajucim objektom cim se inicijalno ucita sam objekat ili nece. Ako je
     * FetchType EAGER ucitace se sve veze sa objektom odmah, a ako je FetchType
     * LAZY ucitace se tek pri eksplicitnom trazenju povezanih objekata (pozivanjem
     * npr. metode getExams). Vise informacija na:
     * https://howtoprogramwithjava.com/hibernate-eager-vs-lazy-fetch-type/
     *
     * Pored atributa fetch moze se iskoristiti i atribut cascade. CascadeType
     * podesen na All dozvoljava da se prilikom svakog cuvanja, izmene ili brisanja
     * studenta cuvaju, menjaju ili brisu i ispiti. To znaci da ne moraju unapred da
     * se cuvaju ispiti pa onda povezuju sa studentom. orphanRemoval podesen na true
     * ce obezbediti da se ispiti izbrisu iz baze kada se izbrisu iz kolekcije
     * ispita u objektu student.
     */
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AppUser> appUsers = new HashSet<AppUser>();

    public Address() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    //kad god postoji bidirekciona veza, obe strane trebaju biti sinhronizovane
    //kroz addChild i removeChild metode u roditeljskom entitetu
    public void addAppUser(AppUser appUser) {
        appUsers.add(appUser);
        appUser.setAddress(this);
    }

    public void removeAppUser(AppUser appUser) {
        appUsers.remove(appUser);
        appUser.setAddress(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address a = (Address) o;
        return id != null && id.equals(a.getId());
    }

    @Override
    public int hashCode() {
        /*
         * Pretpostavka je da je u pitanju tranzijentni objekat (jos nije sacuvan u bazu) i da id ima null vrednost.
         * Kada se sacuva u bazu dobice non-null vrednost. To znaci da ce objekat imati razlicite kljuceve u dva stanja, te ce za generisan
         * hashCode i equals vratiti razlicite vrednosti. Vracanje konstantne vrednosti resava ovaj problem.
         * Sa druge strane ovakva implementacija moze da afektuje performanse u slucaju velikog broja objekata
         * koji ce zavrsiti u istom hash bucket-u.
         */
        return 1337;
    }

    /*
     * Pri implementaciji equals and hashCode metoda treba obratiti paznju da se
     * one razlikuju kada se koristi ORM (Hibernate) i kada se klase posmatraju kao
     * obicne POJO klase. Hibernate zahteva da entitet mora biti jednak samom sebi kroz sva
     * stanja tog objekta (tranzijentni (novi objekat), perzistentan (persistent), otkacen (detached) i obrisan (removed)).
     * To znaci da bi dobar pristup bio da se za generisanje equals i hashCode metoda koristi podatak
     * koji je jedinstven a poznat unapred (tzv. business key) npr. index studenta, isbn knjige, itd.
     * U slucaju da takvog obelezja nema, obicno se implementacija svodi na proveri da li je id (koji je kljuc) isti.
     * Posto u velikom broju slucajeva id baza generise, to znaci da u tranzijentnom stanju objekti nisu jednaki.
     * Postoji nekoliko resenja za ovaj problem:
     * 1. Naci neko jedinstveno obelezje
     * 2. Koristiti prirodne kljuceve
     * 3. Pre cuvanja na neki nacin saznati koja je sledeca vrednost koju ce baza generisati pa pozvati setId metodu da se kompletira objekat cak i pre cuvanja
     * 4. Na drugi nacin implementirati equals i hashCode - primer u klasi Teacher
     */
}
