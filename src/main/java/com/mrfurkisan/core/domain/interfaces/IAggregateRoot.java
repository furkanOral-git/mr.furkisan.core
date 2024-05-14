package com.mrfurkisan.core.domain.interfaces;

import com.mrfurkisan.core.domain.functional.IFunctionalInterface;
import com.mrfurkisan.core.domain.functional.IVoidFunctionalInterface;

//Agrege kök, varlıkları kendi içerisinde barındıran ve sadece kendisinin kapsadığı varlıklarla ilgilenen
//Domain Driven Design'ın önemli bir parçasıdır. Örn: Bir Sepet Domaininde sepetle alakalı varlık: CartItem olurken, Cart bir agrege kökdür.
//Agrege Köklerde içerideki root data structure(List,LinkedList,HashSet,HashMap) yapısına direkt erişimi kapattım. Sebebi 
//encapsulation'ı sağlamak amacıyla. Verilere erişimin ve manipüle edilmesinin sadece AggregateRoot'lar aracılığıyla yapmak istediğim için. 
public interface IAggregateRoot<TEntity extends IEntity, TId> {

    public void AddToRoot(TEntity entity);

    public void RemoveFromRoot(TEntity entity);

    // Her bir nesne için callback fonksiyonunu çalıştırmasını sağlar.
    public void ForEach(IVoidFunctionalInterface<TEntity> callBack);

    
   
}
