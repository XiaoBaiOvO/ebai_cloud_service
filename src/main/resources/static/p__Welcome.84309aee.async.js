"use strict";(self.webpackChunkebai_cloud_web=self.webpackChunkebai_cloud_web||[]).push([[185],{65371:function(Ue,$,t){t.r($),t.d($,{default:function(){return Le}});var te=t(17061),d=t.n(te),ne=t(17156),A=t.n(ne),se=t(27424),I=t.n(se),Z=t(2618),re=t(22263),G=t(58812),ae=t(75491),ie=t(19650),b=t(38272),W=t(51890),m=t(67294),e=t(85893),w=function(i){var l=i.icon,r=i.text;return(0,e.jsxs)(ie.Z,{children:[m.createElement(l),r]})},le=function(){var i=(0,m.useState)({}),l=I()(i,2),r=l[0],y=l[1],N=function(){var D=A()(d()().mark(function o(){return d()().wrap(function(u){for(;;)switch(u.prev=u.next){case 0:return u.t0=y,u.next=3,(0,Z.Ik)();case 3:u.t1=u.sent,(0,u.t0)(u.t1);case 5:case"end":return u.stop()}},o)}));return function(){return D.apply(this,arguments)}}();return(0,m.useEffect)(function(){N().then()},[]),(0,e.jsx)("div",{children:(0,e.jsx)(b.ZP,{itemLayout:"vertical",size:"large",dataSource:r.newsList,renderItem:function(o){return(0,e.jsxs)(b.ZP.Item,{actions:[(0,e.jsx)(w,{icon:re.Z,text:"156"},"list-vertical-star-o"),(0,e.jsx)(w,{icon:G.Z,text:"156"},"list-vertical-like-o"),(0,e.jsx)(w,{icon:ae.Z,text:"2"},"list-vertical-message"),(0,e.jsx)("span",{children:o.focus_date},"time")],extra:(0,e.jsx)("a",{href:o.url,children:(0,e.jsx)("img",{width:272,alt:"logo",src:o.image})}),children:[(0,e.jsx)(b.ZP.Item.Meta,{avatar:(0,e.jsx)(W.C,{src:"https://sf3-cdn-tos.douyinstatic.com/obj/eden-cn/uhbfnupkbps/toutiao_favicon.ico"}),title:(0,e.jsx)("a",{href:o.url,children:o.title}),description:o.keywords}),o.brief]},o.title)}})})},oe=le,ce=t(38743),z=t(80016),de=t(59466),F=t(91894),V=t(71230),ue=t(15746),he=t(80177),me=t(6073),ve=t(81825),H=t(18036),xe=t(4107),Q=t(45777),M=t(31990),Y=t(71257),k=t(71577),v=t(27049),je=t(94418),J=xe.Z.Search,pe=function(){var i=(0,m.useState)({key:"",isAction:!1}),l=I()(i,2),r=l[0],y=l[1],N=(0,m.useState)(!1),D=I()(N,2),o=D[0],P=D[1],u=(0,m.useState)(""),K=I()(u,2),O=K[0],X=K[1],we=(0,m.useState)([]),q=I()(we,2),ze=q[0],E=q[1],Fe=(0,H.useModel)("@@initialState"),Me=Fe.initialState,c=Me.currentUser,Ne=function(n){X(n.target.value)},_=function(){var C=A()(d()().mark(function n(){return d()().wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.t0=E,a.next=3,(0,Z.Rw)();case 3:a.t1=a.sent,(0,a.t0)(a.t1);case 5:case"end":return a.stop()}},n)}));return function(){return C.apply(this,arguments)}}();(0,m.useEffect)(function(){_().then()},[]);var ee=function(){if(!!O){var n={userid:c.userid,name:c.name,avatar:c.avatar,content:O};P(!0),(0,Z.q7)(n).then(function(){X(""),_().then(),P(!1)})}},Pe=function(n){var x={id:n.id,userid:c.userid},a=function(){var j=A()(d()().mark(function h(){return d()().wrap(function(s){for(;;)switch(s.prev=s.next){case 0:return s.t0=E,s.next=3,(0,Z.xG)(x);case 3:s.t1=s.sent,(0,s.t0)(s.t1);case 5:case"end":return s.stop()}},h)}));return function(){return j.apply(this,arguments)}}(),U=function(){var j=A()(d()().mark(function h(){return d()().wrap(function(s){for(;;)switch(s.prev=s.next){case 0:return s.t0=E,s.next=3,(0,Z.x4)(x);case 3:s.t1=s.sent,(0,s.t0)(s.t1);case 5:case"end":return s.stop()}},h)}));return function(){return j.apply(this,arguments)}}(),B=function(){r.key?r.key==n.id+"0"?y({key:n.id+"0",isAction:!r.isAction}):y({key:n.id+"0",isAction:r.isAction}):y({key:n.id+"0",isAction:!r.isAction})},L=function(){var j=A()(d()().mark(function h(R){var s;return d()().wrap(function(p){for(;;)switch(p.prev=p.next){case 0:return s={id:n.id,userid:c.userid,name:c.name,avatar:c.avatar,content:R},p.t0=E,p.next=4,(0,Z.xR)(s);case 4:p.t1=p.sent,(0,p.t0)(p.t1),B();case 7:case"end":return p.stop()}},h)}));return function(R){return j.apply(this,arguments)}}();return[(0,e.jsx)(Q.Z,{title:"Like",children:(0,e.jsxs)("span",{onClick:a,children:[(0,m.createElement)(n.likes.indexOf(c.userid)!=-1?he.Z:G.Z),(0,e.jsx)("span",{children:n.likes.length})]})},"comment-basic-like"),(0,e.jsx)(Q.Z,{title:"Dislike",children:(0,e.jsxs)("span",{onClick:U,children:[m.createElement(n.dislikes.indexOf(c.userid)!=-1?me.Z:ve.Z),(0,e.jsx)("span",{children:n.dislikes.length})]})},"comment-basic-dislike"),(0,e.jsx)("span",{onClick:B,children:"Reply to"},"comment-basic-reply-to"),r.key==n.id+"0"&&r.isAction?(0,e.jsx)(J,{onSearch:L,enterButton:"sent",size:"small",style:{marginBottom:-5}}):null]},Oe=function(n,x){var a=function(){r.isAction?r.key==n.id+x.id?y({key:n.id+x.id,isAction:!r.isAction}):y({key:n.id+x.id,isAction:r.isAction}):y({key:n.id+x.id,isAction:!r.isAction})},U=function(){var B=A()(d()().mark(function L(j){var h;return d()().wrap(function(s){for(;;)switch(s.prev=s.next){case 0:return h={id:n.id,userid:c.userid,name:c.name,avatar:c.avatar,content:j},s.t0=E,s.next=4,(0,Z.xR)(h);case 4:s.t1=s.sent,(0,s.t0)(s.t1),a();case 7:case"end":return s.stop()}},L)}));return function(j){return B.apply(this,arguments)}}();return[(0,e.jsx)("span",{onClick:a,children:"Reply to"},"comment-basic-reply-to"),r.key==n.id+x.id&&r.isAction?(0,e.jsx)(J,{onSearch:U,enterButton:"sent",size:"small",style:{marginBottom:-5}}):null]};return(0,e.jsxs)("div",{style:{marginLeft:20,marginRight:20,marginTop:20},children:[(0,e.jsx)(M.Z,{style:{marginTop:-16},avatar:(0,e.jsx)(W.C,{src:c.avatar,alt:""}),content:(0,e.jsxs)("div",{children:[(0,e.jsx)(Y.Z.Item,{children:(0,e.jsx)(je.Z,{rows:4,value:O,onChange:Ne,autoSize:{minRows:3},onPressEnter:ee})}),(0,e.jsx)(Y.Z.Item,{children:(0,e.jsx)(k.Z,{htmlType:"submit",loading:o,onClick:ee,type:"primary",children:"Add Comment"})})]})}),(0,e.jsx)(b.ZP,{itemLayout:"horizontal",style:{marginTop:-32},dataSource:ze,renderItem:function(n){return(0,e.jsxs)("div",{children:[(0,e.jsx)(M.Z,{style:{marginTop:-16},actions:Pe(n),author:n.author,avatar:n.avatar,content:n.content,datetime:n.datetime,children:(0,e.jsx)(b.ZP,{style:{marginTop:-16},dataSource:n.reply,locale:{emptyText:" "},renderItem:function(a){return(0,e.jsx)(M.Z,{actions:Oe(n,a),author:a.author,avatar:a.avatar,content:a.content,datetime:a.datetime})}})}),(0,e.jsx)(v.Z,{style:{marginTop:-12}})]})}})]})},fe=pe,ge=t(92977),ye=t(18613),Ze=t(28682),Se=t(13013),Ae=t(37744),Te=t(60331),f=t(4914),g=t(37636),Ce=(0,e.jsx)(Ze.Z,{items:[{key:"1",label:(0,e.jsx)("a",{target:"_blank",rel:"noopener noreferrer",href:"http://www.alipay.com/",children:"1st menu item"})},{key:"2",label:(0,e.jsx)("a",{target:"_blank",rel:"noopener noreferrer",href:"http://www.taobao.com/",children:"2nd menu item"})},{key:"3",label:(0,e.jsx)("a",{target:"_blank",rel:"noopener noreferrer",href:"http://www.tmall.com/",children:"3rd menu item"})}]}),Ie=function(){return(0,e.jsx)(Se.Z,{overlay:Ce,placement:"bottomRight",children:(0,e.jsx)(k.Z,{type:"text",icon:(0,e.jsx)(ge.Z,{style:{fontSize:20}})})},"more")},T=function(i){var l=i.src,r=i.text;return(0,e.jsxs)("a",{className:"example-link",children:[(0,e.jsx)("img",{className:"example-link-icon",src:l,alt:r}),r]})},be=function(i){var l=i.children;return(0,e.jsx)(V.Z,{children:(0,e.jsx)("div",{style:{flex:1},children:l})})},ke=function(){var i=(0,H.useModel)("@@initialState"),l=i.initialState,r=l.currentUser;return(0,e.jsx)("div",{children:(0,e.jsx)(Ae.Z,{title:r.name,className:"site-page-header",subTitle:"\u9AD8\u7EA7\u4F1A\u5458",tags:(0,e.jsx)(Te.Z,{color:"blue",children:"LV: 60"}),extra:[(0,e.jsx)(k.Z,{children:"Operation"},"3"),(0,e.jsx)(k.Z,{children:"Operation"},"2"),(0,e.jsx)(k.Z,{type:"primary",children:"Primary"},"1"),(0,e.jsx)(Ie,{},"more")],avatar:{src:r.avatar},children:(0,e.jsxs)(be,{children:[(0,e.jsx)(v.Z,{style:{marginTop:0,marginBottom:10}}),(0,e.jsxs)(f.Z,{layout:"vertical",children:[(0,e.jsx)(f.Z.Item,{label:"UserName",children:"Zhou Maomao"}),(0,e.jsx)(f.Z.Item,{label:"Telephone",children:"18888888888"}),(0,e.jsx)(f.Z.Item,{label:"Live",children:"Shanghai, China"})]}),(0,e.jsx)(v.Z,{style:{marginTop:0,marginBottom:10}})," ",(0,e.jsxs)(f.Z,{layout:"vertical",children:[(0,e.jsx)(f.Z.Item,{label:"UserName",children:"Zhou Maomao"}),(0,e.jsx)(f.Z.Item,{label:"Telephone",children:"18888888888"}),(0,e.jsx)(f.Z.Item,{label:"Live",children:"Shanghai, China"})]}),(0,e.jsx)(v.Z,{style:{marginTop:0,marginBottom:20}}),(0,e.jsxs)("div",{children:[(0,e.jsx)(T,{src:"https://gw.alipayobjects.com/zos/rmsportal/MjEImQtenlyueSmVEfUD.svg",text:" aaa"}),(0,e.jsx)(v.Z,{type:"vertical"}),(0,e.jsx)(T,{src:"https://gw.alipayobjects.com/zos/rmsportal/NbuDUAuBlIApFuDvWiND.svg",text:" bbb"}),(0,e.jsx)(v.Z,{type:"vertical"}),(0,e.jsx)(T,{src:"https://gw.alipayobjects.com/zos/rmsportal/ohOEPSYdDTNnyMbGuyLb.svg",text:" ccc"}),(0,e.jsx)(v.Z,{type:"vertical"}),(0,e.jsx)(T,{src:"https://gw.alipayobjects.com/zos/rmsportal/MjEImQtenlyueSmVEfUD.svg",text:" aaa"}),(0,e.jsx)(v.Z,{type:"vertical"}),(0,e.jsx)(T,{src:"https://gw.alipayobjects.com/zos/rmsportal/NbuDUAuBlIApFuDvWiND.svg",text:" bbb"}),(0,e.jsx)(v.Z,{type:"vertical"}),(0,e.jsx)(T,{src:"https://gw.alipayobjects.com/zos/rmsportal/ohOEPSYdDTNnyMbGuyLb.svg",text:" ccc"})]}),(0,e.jsx)(v.Z,{style:{marginTop:20,marginBottom:20}}),(0,e.jsxs)(g.Z,{children:[(0,e.jsx)(g.Z.Item,{color:"green",children:"Create a services site 2015-09-01"}),(0,e.jsx)(g.Z.Item,{color:"green",children:"Create a services site 2015-09-01"}),(0,e.jsxs)(g.Z.Item,{color:"red",children:[(0,e.jsx)("p",{children:"Solve initial network problems 1"}),(0,e.jsx)("p",{children:"Solve initial network problems 2"}),(0,e.jsx)("p",{children:"Solve initial network problems 3 2015-09-01"})]}),(0,e.jsxs)(g.Z.Item,{children:[(0,e.jsx)("p",{children:"Technical testing 1"}),(0,e.jsx)("p",{children:"Technical testing 2"}),(0,e.jsx)("p",{children:"Technical testing 3 2015-09-01"})]}),(0,e.jsxs)(g.Z.Item,{color:"gray",children:[(0,e.jsx)("p",{children:"Technical testing 1"}),(0,e.jsx)("p",{children:"Technical testing 2"}),(0,e.jsx)("p",{children:"Technical testing 3 2015-09-01"})]}),(0,e.jsxs)(g.Z.Item,{color:"gray",children:[(0,e.jsx)("p",{children:"Technical testing 1"}),(0,e.jsx)("p",{children:"Technical testing 2"}),(0,e.jsx)("p",{children:"Technical testing 3 2015-09-01"})]}),(0,e.jsx)(g.Z.Item,{color:"#00CCFF",dot:(0,e.jsx)(ye.Z,{}),children:(0,e.jsx)("p",{children:"Custom color testing"})})]})]})})})},De=ke,Ee={height:"200px",color:"#fff",lineHeight:"200px",textAlign:"center",background:"#4a8cfe",fontSize:30},Be={height:"200px",color:"#fff",lineHeight:"200px",textAlign:"center",background:"#d79ad0",fontSize:30},Re=function(){return(0,e.jsxs)(ce._z,{children:[(0,e.jsxs)(de.Z,{autoplay:!0,children:[(0,e.jsx)("div",{children:(0,e.jsx)("h3",{style:Ee,children:"\u7F51\u7AD9\u7EF4\u62A4\u4E2D.."})}),(0,e.jsx)("div",{children:(0,e.jsx)("h3",{style:Be,children:"\u656C\u8BF7\u671F\u5F85.."})})]}),(0,e.jsxs)(z.Z,{wrap:!0,ghost:!0,gutter:[20,20],children:[(0,e.jsx)(z.Z,{colSpan:{xs:24,sm:24,md:24,lg:24,xl:18},bodyStyle:{padding:0},children:(0,e.jsx)(F.Z,{title:"\u70ED\u70B9\u65B0\u95FB",bodyStyle:{padding:0},children:(0,e.jsx)(oe,{})})}),(0,e.jsx)(z.Z,{colSpan:{xs:24,sm:24,md:24,lg:24,xl:6},bodyStyle:{padding:0},children:(0,e.jsx)(F.Z,{title:"\u4E2A\u4EBA\u4E2D\u5FC3",bodyStyle:{padding:0},children:(0,e.jsx)(De,{})})})]}),(0,e.jsx)("br",{}),(0,e.jsx)(V.Z,{gutter:20,children:(0,e.jsx)(ue.Z,{span:18,children:(0,e.jsx)("div",{children:(0,e.jsx)(F.Z,{style:{marginBottom:24},title:"\u5C0F\u767D\u4E91\u793E\u533A",bordered:!1,bodyStyle:{padding:0},children:(0,e.jsx)(fe,{})})})})})]})},Le=Re}}]);