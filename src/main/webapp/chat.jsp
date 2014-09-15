<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="ua.chat.webchat.core.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.text.DateFormat" %><html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Chat</title>
        <link rel="stylesheet" type="text/css" href="assets/styles.css" />
        <script type="text/javascript" src="assets/jQuery.js"></script>
        <script type="text/javascript" src="assets/jquery.timers-1.2.js"></script>
        <script type="text/javascript">
            var isAlreadyDownloading = false;

            $(document).ready(function () {

                $('#btnSend').click(function () {
                    SendMessage($(this).val());
                });

                DownloadMessages();
                DownloadUsers();

                $(document).everyTime("1s", function () {
                    DownloadMessages();
                }, 0);

                $(document).everyTime("30s", function () {
                    DownloadUsers();
                }, 0);
            });

            function DownloadMessages() {
                if (!isAlreadyDownloading)
                {
                    isAlreadyDownloading = true;

                    $.get('MessageLog', function (data) {

                        $('#logContainer').html(data);
                        $("#logContainer").attr({scrollTop: $("#logContainer").attr("scrollHeight")});
                    });

                    isAlreadyDownloading = false;
                }
            }

            function DownloadUsers() {
                if (!isAlreadyDownloading)
                {
                    isAlreadyDownloading = true;

                    $.get('UserLog', function (data) {
                        $('#userlist').html(data);
                    });

                    isAlreadyDownloading = false;
                }
            }

            function SendMessage(str) {


                var er = $("#outcomingmess").val();
                var qr = "";

                if (er == qr) {
                } else {
                    $('#btnSend').attr('disabled', 'disabled'); // To disable
                    $.post('MessageLog', $('#chat').serialize(), function (data) {
                        $('#outcomingmess').val("");
                        DownloadMessages();
                        $('#btnSend').removeAttr('disabled'); // To enable
                    });
                }
            }
        </script>
    </head>
    <body>
        <form method="POST" action='Controller' name="chat" id="chat">
            <table cellpadding="6">
                <tr>
                    <td width="600px">
                        <div id="logContainer" style="height:300px; overflow-y:scroll;">
                            <ul id="chatLog">
                            </ul>
                        </div>
                    </td>
                    <td width="150px">
                        <div id="userlist">
                            <ul id="userLog">
                            </ul>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="600px"><textarea id="outcomingmess" name="outcomingmess" style="width:100%;height:100px"></textarea></td>
                </tr>
                <tr>
                    <td align="center">
                        <button type="button" name="btnSend" id="btnSend" value="Send">Send</button> 
                    </td>
                    <td align="right" width="150px">
                        <input type="submit" width="80px" name="btnLogout" value="Logout" onclick="closeWindow()"> 
                    </td>
                </tr>
            </table>
            <input type="hidden" id="nickname" name="nickname" value=<%= request.getAttribute("Username")%>>
            <input type="hidden" id="password" name="password" value=<%= request.getAttribute("Password")%>>
        </form>
    </body>
</html>
