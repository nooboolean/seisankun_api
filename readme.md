# seisankun_api
> A Spring Boot project
## 環境
### バージョン
 - Java : 11.0.3 (Amazon Corretto)
 - gradle : 6.0.1
 - Spring Boot : v2.2.3
### 本番環境URL
 - https://seisan-kun-api.herokuapp.com
### 開発環境URL
- http://localhost:{ポートはご自由に(デフォルトは1234)}
### フロントエンド
 - seisankun_front https://github.com/nooboolean/seisankun_front
 
 ## 初期準備
 1.MySQLの用意
 
 ※DDLやユーザーなどは別途開発者から聞いてください
 
 2.本repositoryをcloneした後、OSの環境変数に以下項目を設定してください
 
 ※Basic認証のユーザーなどは、別途開発者から聞いてください
```shell script
SEISANKUN_API_DATABASE_PATH=DB_NAME
SEISANKUN_API_DATABASE_USER=DB_USER
SEISANKUN_API_DATABASE_PASSWORD=DB_PASSWORD
SEISANKUN_API_BASIC_USER=BASIC_AUTH_USER
SEISANKUN_API_BASIC_PASSWORD=BASIC_AUTH_PASSWORD
SEISANKUN_API_DATABASE_HOST=DB_HOST(port番号指定あれば指定)
```

## Build Setup
### jarファイルによる起動
 - gradlewによるbuild
```shell script
$ ./gradlew build
```

 -  アプリケーションサーバの起動
```shell script
$ java -jar build/libs/seisankun_api-0.0.1-SNAPSHOT.jar
```

### gradleのタスクによる起動
 - gradlew
```shell script
$ gradlew bootRun
```