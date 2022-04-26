package com.tomatokey.prototype.configuration;

/**
 * AopやFilterなどの実行順序の管理クラス
 * 実行順序は小さいほど優先度が高くなります
 * @Orderを指定しない、または@Orderでvalueを指定しない場合のデフォルト実行順序はInteger.MAX_VALUEとなります
 */
public class ComponentOrder {

    public static final int SwitchingDataSourceAop = 100;

}
