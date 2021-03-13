<#assign path=basePath/>
<#import "../fragments/ui.ftl" as ui/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <@ui.head  basePath=path/>
</head>
<body>
<header>
  <@ui.header title="Spring mvc tese list"  basePath=path/>
</header>
<div class="main-content">
    <h2>list</h2>

    

        <table border="1">
          <tr> 
            <th>id</th>             
            <th>names</th>
            <th>phones</th>     
         </tr> 

         <#list users as user>
          <tr>
             <td>${user.id}</td>
             <td>${user.name}</td>
             <td>${user.phone}</td>
           </tr>
         </#list>  

       </table>

    
    <a href="${basePath}/download/pdf">model pdf</a>
    <a href="${basePath}/download/xls">model xls</a>
    <a href="${basePath}/download/file/userslist.json">file userslist</a>
    <a href="${basePath}/download/excel">stream excel</a>
    <a href="${basePath}/download/pdfdoc">stream pdfdoc</a>
    <a href="${basePath}/download/csv">stream csv</a>
</div>


<footer>
<@ui.footer  basePath=path/>
</footer>

</body>
</html>