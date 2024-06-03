package com.mrfurkisan.core.infrastructure.security.messages;

public abstract class SessionErrorMessages {
    public static String ChangePassword = "Şifreniz değiştirlirken bir hata oluştu. Lütfen daha sonra tekrar deneyiniz!";
    public static String ChangeEmail = "Emailiniz değiştirilirken bir hata oluştu. Lütfen daha sonra tekrar deneyiniz!";
    public static String ChangeUsername = "Kullanici adiniz değiştirilirken bir hata oluştu. Lütfen daha sonra tekrar deneyiniz!";
    public static String SignIn = "Kayit tamamlanırken bir hata oluştu! Lütfen daha sonra tekrar deneyiniz!";
    public static String NotValidatedToken = "Geçersiz token kullanildi!";
    public static String NotAuthorized = "Erişim izniniz yok!";
    public static String ExistUsername = "Böyle bir kullanici adi zaten kullaniliyor!";
    public static String ExistEmail = "Böyle bir email zaten kullaniliyor!";
    public static String SamePassword = "Yeni şifreniz, bir önceki ile ayni olamaz!";
    public static String NotValidEmail = "Lütfen geçerli bir email formati kullanin!";
    public static String NotAuhenticated = "Kullanici adi veya şifreniz yanliş!";
    public static String NotExistUsernameOrEmail = "Böyle bir kullanici yok!";
}
