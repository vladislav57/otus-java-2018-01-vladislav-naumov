<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        var stompClient = null;

        function sendRequest() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/controller/cachecountersjson", true);
            xhr.send();
            xhr.onreadystatechange = function () {
                var data = JSON.parse(xhr.responseText);
                document.getElementById("hit").textContent = data["HitCount"];
                document.getElementById("miss").textContent = data["MissCount"];
            }
        }
        function connect() {
            var socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
            });
        }

        function disconnect() {
            if(stompClient != null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }

        function sendMessage() {
            stompClient.send("/app/chat", {}, "");
        }
    </script>
</head>
    <body>
        <table>
            <tr>
                <td>Hit count: </td><td id="hit">${hitCount}</td>
            </tr>
            <tr>
                <td>Miss count: </td><td id="miss">${missCount}</td>
            </tr>
        </table>
    </body>
    <div>
        <button name="toPress" onclick="sendRequest()">Press me</button>
    </div>
    <div>
        <button name="toPress" onclick="connect()">Connect</button>
    </div>
    <div>
        <button name="toPress" onclick="sendMessage()">Send message</button>
    </div>
    <div>
        <button name="toPress" onclick="disconnect()">Disconnect</button>
    </div>
</html>
