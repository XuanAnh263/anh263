
    "use strict";
    function getConversations() {
        let jwtToken = localStorage.getItem("jwtToken");
        console.log(jwtToken);
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
        // conversationList.empty();

        conversations.forEach(function (conversation) {
            let chatLink = `
      <a class="onversation-link text-reset nav-link p-0 mb-6" href="./chat${conversation.id}">
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


            conversationList.append(chatLink);
        });
    }

    // Gọi hàm để lấy danh sách cuộc trò chuyện khi trang web được tải
    $(document).ready(function () {
        let jwtToken = localStorage.getItem("jwtToken");
        getConversations(jwtToken);

    });

