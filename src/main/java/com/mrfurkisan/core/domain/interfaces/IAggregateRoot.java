package com.mrfurkisan.core.domain.interfaces;

import com.mrfurkisan.core.domain.functional.IVoidAccumulatorFunctionalInterface;
import com.mrfurkisan.core.domain.functional.IVoidLambdaFunctionalInterface;

//Agrege kök, varlıkları kendi içerisinde barındıran ve sadece kendisinin kapsadığı varlıklarla ilgilenen
//Domain Driven Design'ın önemli bir parçasıdır. Örn: Bir Sepet Domaininde sepetle alakalı varlık: CartItem olurken, Cart bir agrege kökdür.
//Agrege Köklerde içerideki root data structure(List,LinkedList,HashSet,HashMap) yapısına direkt erişimi kapattım. Sebebi 
//encapsulation'ı sağlamak amacıyla. Verilere erişimin ve manipüle edilmesinin sadece AggregateRoot'lar aracılığıyla yapmak istediğim için. 
public interface IAggregateRoot<TEntity extends IEntity, TId> {

    public void AddToRoot(TEntity entity);

    public void RemoveFromRoot(TEntity entity);

    // Her bir nesne için callback fonksiyonunu çalıştırmasını sağlar.
    public void ForEach(IVoidLambdaFunctionalInterface<TEntity> callBack);

    // Javascript içerisindeki accümülator yapısının aggregate rootlara eklenmesi.
    // Örneğin bir cart'taki ürünlerin
    // toplam fiyatını recursive şekilde eklemek için bu fonksiyon kullanılabilir.
    // TReturnType Accumulate fonksiyonuna parametre olarak gönderilen Lambda
    // fonksiyonu içerisindeki toplayıcıyı temsil eder temsil eder.
    // Cart senaryosunda integer bir değer olacak ve her adımda entity.Price
    // özelliğini accümülator'a ekleyebilmemizi sağlayacaktır.
    public <TReturnType extends IBaseValueObject> TReturnType Accumulate(
            IVoidAccumulatorFunctionalInterface<TEntity, TReturnType> callBack, TReturnType accumulator);
}
