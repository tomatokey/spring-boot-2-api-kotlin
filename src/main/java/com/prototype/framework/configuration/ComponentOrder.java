package com.prototype.framework.configuration;

import org.springframework.core.annotation.Order;

/**
 * AopやFilterなどの実行順序の管理クラス
 * 実行順序は小さいほど優先度が高くなります
 * {@link Order}を指定しない、または
 * {@link Order}でvalueを指定しない場合のデフォルト実行順序はInteger.MAX_VALUEとなります
 */
public class ComponentOrder {

    public static final int SwitchingDataSourceAop = 100;

}
