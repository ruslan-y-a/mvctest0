<#assign path=basePath/>
<#assign base=request.contextPath/>
<#import "fragments/ui.ftl" as ui/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <@ui.head basePath=path/>
</head>
<body>
<header>
  <@ui.header title="Spring mvc freemaker upload" basePath=path/>
</header>
<div class="main-content">
    <h2>upload</>

   <form action="upload" method="post" enctype="multipart/form-data">
         file:<input type="file" name="file"/>
       <input type="submit"/>
   </form>
</div>


<footer>
<@ui.footer basePath=path/>
</footer>

</body>
</html>