<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function sendRequest() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/controller/test", false);
            xhr.send();
            alert(xhr.responseText);
        }
    </script>
</head>
    <body>
        <table>
            <tr>
                <td>Hit count: </td><td>${hitCount}</td>
            </tr>
            <tr>
                <td>Miss count: </td><td>${missCount}</td>
            </tr>
        </table>
    </body>
    <div>
        <button name="toPress" onclick="sendRequest()">Press me</button>
    </div>
</html>
