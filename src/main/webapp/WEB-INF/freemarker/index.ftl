<#import "fragments/ui.ftl" as ui/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <@ui.head/>
</head>
<body>
<header>
  <@ui.header title="Spring mvc tese freemaker"/>
</header>
<div class="main-content">
    <h2>HELLO</>

     <a href="${basePath}upload">upload page</a>
     <a href="${basePath}download/list">upload page</a>
</div>


<footer>
<@ui.footer/>
</footer>

</body>
</html>