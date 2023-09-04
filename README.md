# 
### 1.Build with

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring WebSocket](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Springfox Swagger UI](http://springfox.github.io/springfox/docs/current/)
* [JSON Web Token](https://jwt.io/)
* [Querydsl](http://querydsl.com/)
* [MySQL](https://www.mysql.com/)

### 2. API & Function

|                | Method | API                                             | Function                                           |
|----------------|--------|-------------------------------------------------|----------------------------------------------------|
| Authentication | POST   | /api/v1/login                                   | Đăng nhập                                          |
| User           | POST   | /api/v1/users/signup                            | Đăng ký                                            |
|                | PUT    | /api/v1/users/change-password                   | Quên mật khẩu                                      |
|                | GET    | /api/v1/users/my-info                           | Hiển thị thông tin người dùng                      |
|                | PUT    | /api/v1/users/{id}                              | Cập nhập thông tin người dùng                      |
|                | GET    | /api/v1/users/{id}                              | Hiển thị người dùng                                |
| Friend         | GET    | /api/v1/friends                                 | Hiển thị bạn bè                                    |
|                | POST   | /api/v1/friendRequest/{userBId}                 | Gửi yêu cầu kết bạn                                |
|                | GET    | /api/v1/friendRequest                           | Hiển thị danh sách đã gửi yêu cầu kết bạn          |
|                | GET    | /api/v1/allFriendAccepted                       | Hiển thị danh sách người dùng đã chấp nhận kết bạn |
|                | PUT    | /api/v1/friendRequest/{id}                      | Cập nhập người dùng sau khi đồng ý kết bạn         |
|                | DELETE | /api/v1/friendRequest/{id}                      | Hủy lời mời kết bạn                                |
| POST           | GET    | /api/v1/posts                                   | Danh sách bài viết                                 |
|                | GET    | /api/v1//posts/{id}                             | Hiển thị chi tiết một bài viết                     |
|                | POST   | /api/v1/posts                                   | Tạo bài viết mới                                   |
|                | PUT    | /api/v1/posts/{id}                              | Chỉnh sửa bài viết                                 |
|                | DELETE | /api/v1/posts/{id}                              | Xóa bài viết                                       |
| LIKE           | GET    | /api/v1/likes/post/{postId}                     | Hiển thị lượt thích của bài viết                   |
|                | POST   | /api/v1/likes/post/{id}                         | Thích một bài viết                                 |
|                | DELETE | /api/v1/likes/post/{id}                         | Bỏ thích bài viết                                  |
| COMMENT        | GET    | /api/v1/comments/post/{postId}                  | Hiển thị bình luận bài viết                        |
|                | POST   | /api/v1/comments/post/{id}                      | Bình luận bài viết                                 |
|                | PUT    | /api/v1/comments/{id}                           | Chỉnh sửa bình luận                                |
|                | DELETE | /api/v1/comments/{id}                           | Xóa bình luận                                      |
| CHAT           | GET    | /api/v1/messages/{senderId}/{recipientId}/count | Hiển thị số lượng tin nhắn mới                     |
|                | GET    | /api/v1/messages/{senderId}/{recipientId}       | Tìm tin nhắn trong cuộc trò chuyện                 |
|                | GET    | /api/v1/messages/{id}                           | Tìm tin nhắn                                       |
| BLOCK          | GET    | /api/v1/block-chats                             | Hiển thị danh sách bị chặn nhắn tin                |
|                | GET    | /api/v1/block-diary                             | Hiển thị danh sách bị chặn                         |
|                | POST   | /api/v1/blockChatRequest/{userBId}              | Thực hiện việc chặn người dùng nhắn tin            |
|                | POST   | /api/v1/blockDiaryRequest/{userBId}             | Thực hiện chặn hoàn toàn người dùng                |
|                |        |                                                 |                                                    |

### 3. DATABASE





