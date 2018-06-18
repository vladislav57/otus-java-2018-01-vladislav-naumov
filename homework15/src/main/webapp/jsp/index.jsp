<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function sendRequest() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/controller/cachecountersjson", false);
            xhr.send();
            var data = JSON.parse(xhr.responseText);
            document.getElementById("hit").textContent = data["HitCount"];
            document.getElementById("miss").textContent = data["MissCount"];
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
</html>
