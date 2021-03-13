<#assign path=basePath/>
<#import "fragments/ui.ftl" as ui/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <@ui.head basePath=path/>
</head>
<body>
<header>
  <@ui.header title="Spring mvc tese freemaker"  basePath=path/>
</header>
<div class="main-content">
    <h2>HELLO</>

     <a href="${path}/upload">upload page</a>
     <a href="${path}/download/list">upload page</a>
</div>


<footer>
<@ui.footer  basePath=path/>
</footer>

</body>
</html>