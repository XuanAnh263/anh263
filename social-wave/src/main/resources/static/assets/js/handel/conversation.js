// $(window).on("load", function () {

$(document).ready(function () {
    function getConversations() {
        let jwtToken = localStorage.getItem("jwtToken");

        if (jwtToken) {
            $.ajax({
                url: "/api/v1/conversations",
                type: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                },
                success: function (data) {
                    updateConversationList(data);
                    document.getElementById("conversation-content").style.visibility = "visible";
                },
                error: function () {
                    toastr.error("Error while fetching conversations!");
                }
            });
        } else {
            toastr.warning("Please log in again!");
            document.getElementById("conversation-content").style.visibility = "hidden";
            window.location.href = 'http://localhost:8080/signin';
        }
    }


    // Hàm để cập nhật danh sách cuộc trò chuyện trong thẻ nav
    function updateConversationList(conversations) {
        let conversationList = $("#conversationList");
        conversationList.empty();
        conversations.forEach(function (conversation) {
            let chatLink = `
      <a class="conversation-link text-reset nav-link p-0 mb-6" href="#" data-conversation-id="${conversation.id}">
        <div class="card card-active-listener">
          <div class="card-body">
            <div class="media">
              <div class="avatar mr-5">
                <img class="avatar-img" src="${conversation.avatar}" alt="Avatar">
              </div>
              <div class="media-body overflow-hidden">
                <div class="d-flex align-items-center mb-1">
                    <h6 class="text-truncate mb-0 mr-auto">${conversation.name}</h6>
                    <p class="small text-muted text-nowrap ml-4">${conversation.lastModifiDateTime}</p>
                </div>
                <div class="text-truncate">${conversation.description}</div>
              </div>
            </div>
          </div>
          <div class="badge badge-circle badge-primary badge-border-light badge-top-right">
            <span>3</span>
          </div>
        </div>

      </a>
    `;
            let chatLinkElement = $(chatLink);
            conversationList.append(chatLinkElement);


            // Chuyển hướng đến trang chat.html cùng với conversationId trong URL
            // window.location.href = `chat?id=${conversationId}`;

        });
    }


    function getMessagesInConversation(conversationId) {
        if (!conversationId) {
            return;
        }
        let jwtToken = localStorage.getItem("jwtToken");
        if (jwtToken) {

            $.ajax({
                url: `/api/v1/messages/${conversationId}`,
                type: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
                },
                success: function (data) {

                    // Lưu trữ dữ liệu tin nhắn vào biến conversationData
                    updateMessageList(data);

                    document.getElementById("conversation-content").style.visibility = "visible";
                },
                error: function () {
                    toastr.error("Error while fetching messages!");
                }
            });
        } else {
            toastr.warning("Please log in again!");
            document.getElementById("conversation-content").style.visibility = "hidden";
            window.location.href = 'http://localhost:8080/signin';

        }

    }

    function updateMessageList(messages) {
        let messageList = $("#content-chat");
        messageList.empty();
        messages.forEach(function (message) {
            let messageHtml = `
                  <!-- Message -->
            <div class="message">
              <!-- Avatar -->
              <a class="avatar avatar-sm mr-4 mr-lg-5" href="#" >
                <img class="avatar-img" src="assets/images/avatars/10.jpg" alt="">
              </a>

              <!-- Message: body -->
              <div class="message-body">

                <!-- Message: row -->
                <div class="message-row">
                  <div class="d-flex align-items-center">

                    <!-- Message: content -->
                    <div class="message-content bg-light">
                      <h6 class="mb-2">${message.sender}</h6>
                      <div>${message.contentText}</div>

                      <div class="mt-1">
                        <small class="opacity-65">${message.creatDateTime}</small>
                      </div>
                    </div>
                    <!-- Message: content -->

                    <!-- Message: dropdown -->
                    <div class="dropdown">
                      <a class="text-muted opacity-60 ml-3" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fe-more-vertical"></i>
                      </a>

                      <div class="dropdown-menu">
                        <a class="dropdown-item d-flex align-items-center" href="#">
                          Edit <span class="ml-auto fe-edit-3"></span>
                        </a>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                          Share <span class="ml-auto fe-share-2"></span>
                        </a>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                          Delete <span class="ml-auto fe-trash-2"></span>
                        </a>
                      </div>
                    </div>
                    <!-- Message: dropdown -->

                  </div>
                </div>
                <!-- Message: row -->

              </div>
              <!-- Message: Body -->
            </div>
            <!-- Message -->

            <!-- Divider -->
            <div class="message-divider my-9 mx-lg-5">
              <div class="row align-items-center">

                <div class="col">
                  <hr>
                </div>

                <div class="col-auto">
                  <small class="text-muted">${message.lastModifiedDateTime}</small>
                </div>

                <div class="col">
                  <hr>
                </div>
              </div>
            </div>
            <!-- Divider -->
                `;
            // if (message.isTyping) {
            //   let messageHtml = `
            //   <div class="message">
            //     <a class="avatar avatar-sm mr-4 mr-lg-5" href="#" data-chat-sidebar-toggle="#chat-2-info">
            //       <img class="avatar-img" src="assets/images/avatars/10.jpg" alt="">
            //     </a>
            //     <div class="message-body">
            //       <div class="message-row">
            //         <div class="d-flex align-items-center">
            //           <div class="message-content bg-light">
            //             <div>Anna is typing<span class="typing-dots"><span>.</span><span>.</span><span>.</span></span></div>
            //           </div>
            //         </div>
            //       </div>
            //     </div>
            //   </div>
            // `;
            // }
            messageList.append(messageHtml);
        });
    }

    $("#conversationList").on("click", ".conversation-link", function (event) {
        event.preventDefault();
        let conversationId = $(this).data("conversation-id");
        if (!conversationId) {
            return;
        }

        getMessagesInConversation(conversationId);

        // Chuyển hướng đến trang chat.html cùng với conversationId trong URL
        window.location.href = `chat?id=${conversationId}`;
    });
    const urlParams = new URLSearchParams(window.location.search);
    const conversationId = urlParams.get("id");

    if (conversationId) {
        getMessagesInConversation(conversationId);
    }

    getConversations();

});
// })





