# SpringBoot(ver.2系)を用いたAPIのプロトタイプ

## 実行環境
| 種別                    | 条件   |
|:-----------------------|:-----|
| JDK                    | 17   |
| Tomcat                 | 9    |

## ローカル環境

### 開発環境（IntelliJ）

#### 必要なプラグイン・設定
- Lombok pluginをインストールする。
- Windowsの場合は、コンソール出力が文字化けするため以下を設定
  - Settings > Editor > General > Console > `Default Encoding` をUTF-8にする
  - `C:¥Program Files¥JetBrains¥IntelliJ Idea xx.x.x¥bin`の中にある`idea64.exe.vmoptions`ファイルに`-Dfile.encoding=UTF-8`を追記する。(Intellijを再起動する)

### Dockerの起動
#### Dockerデーモンの起動
ローカル環境用にMySQLサーバーを立ち上げる必要があるため、  
事前に [DockerDesktop](https://docs.docker.com/get-docker/) のインストールと起動を行ってください。  

#### Dockerの起動
以下のコマンドでDockerを起動してください。
```bash
$ ./gradlew :dockerComposeUp
```

### アプリケーションの起動
デバッグモードを使用したい場合は、Terminalからではなく、  
IntellijならGradleメニューから実行すると簡単です。
```bash
$ ./gradlew :bootRun
```
※事前にDockerを起動する必要があります

### リリース手順
tomcat組み込みの単体で起動したい場合や、  
tomcatを別途用意する場合のどちらでも起動可能なwarの生成を行います。
```bash
$ ./gradlew :bootWar
```
上記のコマンドを実行すると、build/libs配下にwarが生成されます。  
組み込みのtomcat(9.0.60)を用いて起動する場合は、以下のコマンドを実行してください。
```bash
$ java -jar build/libs/spring-boot-2-api.war --spring.profiles.active=production
```
組み込みのtomcatを使わない場合など、  
プロファイルの選択を環境変数に設定する場合は以下のようにしてください。  
```bash
$ export SPRING_PROFILES_ACTIVE=production
```

### IF情報
[SwaggerUI](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config) にてIF情報を確認することができます。  
確認する場合は、事前にアプリケーションの起動が必要となります。

## 使用ライブラリ

| 名称                                                                                           | 説明                             |
|:---------------------------------------------------------------------------------------------|:-------------------------------|
| [Spring Web](https://spring.io/guides/gs/serving-web-content/)                               | RestフルなAPIを作成するために使用しています      |
| [Spring Data Jdbc](https://spring.pleiades.io/spring-data/jdbc/docs/current/reference/html/) | JDBCを用いたDBアクセスを簡略化するために使用しています |
| [Spring Security](https://projects.spring.io/spring-security/)                               | セキュリティ対策、認証・認可のために使用しています      |
| Spring Aop                                                                                   | Datasourceの切り替え用に使用しています       |
| Spring Validation                                                                            | リクエストパラメータのバリデーションのために使用しています  |
| [Lombok Project](https://projectlombok.org/)                                                 | 定型的なコードを書かなくてもよくするために使用しています   |
