<%--
  Created by IntelliJ IDEA.
  User: jacobd
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta content="text/html; charset=UTF-8"
        http-equiv="Content-Type"/>
  <title>RaceTrack</title>
</head>

<body>
<div class="body">
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
</div>

<g:each in="${raceInstanceList}"
        status="i" var="raceInstance">
  <div class="race">
    <h2>${raceInstance.name}</h2>

    <p class="race-details">
      <span class="question">When?</span>
      <span class="answer">
        ${raceInstance.startDate.format("EEEE, MMMM d, yyyy")}
      </span>
    </p>

    <p class="race-details">
      <span class="question">Where?</span>
      <span class="answer">
        ${raceInstance.city}, ${raceInstance.state}
      </span>
    </p>

    <p class="race-details">
      <span class="question">How Long?</span>
      <span class="answer">
        <g:formatNumber
            number="${raceInstance.distance}"
            format="0 K"/>
      </span>
    </p>

    <p class="race-details">
      <span class="question">How Much?</span>
      <span class="answer">
        <g:formatNumber number="${raceInstance.cost}"
                        format="\$###,##0"/>
      </span>
    </p>
  </div>
</g:each>
<div class="paginateButtons">
  <g:paginate total="${raceInstanceTotal}"/>
</div>

</body>
</html>