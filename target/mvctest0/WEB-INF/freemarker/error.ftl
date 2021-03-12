<#assign base=request.contextPath/>
<#import "fragments/ui.ftl" as ui/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <@ui.head/>
</head>
<body>
<header>
  <@ui.header title="Spring mvc freemaker upload"/>
</header>
<div class="main-content">
         <h2>Exception occured at: ${exception.date}</h2>
          <h2>Exception Message   : ${exception.message}</h2>
</div>

<footer>
<@ui.footer/>
</footer>

</body>
</html>