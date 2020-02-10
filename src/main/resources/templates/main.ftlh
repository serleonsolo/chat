<#import "parts/common.ftlh" as c>
<#import "parts/log.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
    <span><a href = "/user">Users List</a></span>
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Message" />
        <input type="text" name="tag" placeholder="Tag">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add Message</button>
    </form>
</div>
<div>Message List</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter}">
    <button type="submit">Search</button>
</form>
<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
<#else>
    No message
</#list>
</@c.page>