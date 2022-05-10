# SpringBoot(ver.2系)を用いたAPIのプロトタイプ

## ローカル環境

### 開発環境（IntelliJ）

#### 必要なプラグイン・設定
- Lombok pluginをインストールする。
- Windowsの場合は、コンソール出力が文字化けするため以下を設定
  - Settings > Editor > General > Console > `Default Encoding` をUTF-8にする
  - `C:¥Program Files¥JetBrains¥IntelliJ Idea xx.x.x¥bin`の中にある`idea64.exe.vmoptions`ファイルに`-Dfile.encoding=UTF-8`を追記する。(Intellijを再起動する)

### Dockerの起動
ローカル環境用にMySQLサーバーを立ち上げます。  
事前に [DockerDesktop](https://docs.docker.com/get-docker/) のインストールと起動が必要となります。  
もしDockerの起動がうまく行かない場合は、mysqlをローカル環境に独自に作成してください。

#### Windows10、MacOSXの場合
```bash
$ cd docker-local
$ docker-compose up -d
```

### アプリケーションの起動
- 「MyApplication」クラスを右クリック > 実行 'MyApplication'

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
