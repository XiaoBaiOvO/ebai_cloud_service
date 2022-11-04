"use strict";(self.webpackChunkebai_cloud_web=self.webpackChunkebai_cloud_web||[]).push([[818],{80016:function(Me,ue,l){l.d(ue,{Z:function(){return p}});var Z=l(87462),t=l(67294),ye=l(3700),Q=l(51752),D=l(1413),c=l(4942),s=l(71002),se=l(29439),pe=l(16286),M=l(75901),de=l(44925),Ne=l(47461),T=l(25378);function X(){return(0,T.Z)()}var fe={useBreakpoint:X},ae=l(8812),ne=l(67552),be=l(94184),U=l.n(be),le=l(97435),Y=l(21770),Ce=function(y){var v=y.actions,u=y.prefixCls;return Array.isArray(v)&&v?.length?t.createElement("ul",{className:"".concat(u,"-actions")},v.map(function(P,x){return t.createElement("li",{style:{width:"".concat(100/v.length,"%")},key:"action-".concat(x)},t.createElement("span",null,P))})):v?t.createElement("ul",{className:"".concat(u,"-actions")},v):null},a=Ce,h=l(13062),f=l(71230),n=l(89032),e=l(15746),r=function(y){var v=y.style,u=y.prefix;return t.createElement("div",{className:"".concat(u,"-loading-content"),style:v},t.createElement(f.Z,{gutter:8},t.createElement(e.Z,{span:22},t.createElement("div",{className:"".concat(u,"-loading-block")}))),t.createElement(f.Z,{gutter:8},t.createElement(e.Z,{span:8},t.createElement("div",{className:"".concat(u,"-loading-block")})),t.createElement(e.Z,{span:15},t.createElement("div",{className:"".concat(u,"-loading-block")}))),t.createElement(f.Z,{gutter:8},t.createElement(e.Z,{span:6},t.createElement("div",{className:"".concat(u,"-loading-block")})),t.createElement(e.Z,{span:18},t.createElement("div",{className:"".concat(u,"-loading-block")}))),t.createElement(f.Z,{gutter:8},t.createElement(e.Z,{span:13},t.createElement("div",{className:"".concat(u,"-loading-block")})),t.createElement(e.Z,{span:9},t.createElement("div",{className:"".concat(u,"-loading-block")}))),t.createElement(f.Z,{gutter:8},t.createElement(e.Z,{span:4},t.createElement("div",{className:"".concat(u,"-loading-block")})),t.createElement(e.Z,{span:3},t.createElement("div",{className:"".concat(u,"-loading-block")})),t.createElement(e.Z,{span:16},t.createElement("div",{className:"".concat(u,"-loading-block")}))))},i=r,m=["className","style","bodyStyle","headStyle","title","subTitle","extra","tip","wrap","layout","loading","gutter","tooltip","split","headerBordered","bordered","children","size","actions","ghost","hoverable","direction","collapsed","collapsible","collapsibleIconRender","defaultCollapsed","onCollapse","checked","onChecked","tabs","type"],g=fe.useBreakpoint,b=t.forwardRef(function(o,y){var v,u,P,x=o.className,B=o.style,R=o.bodyStyle,L=R===void 0?{}:R,K=o.headStyle,k=K===void 0?{}:K,q=o.title,_=o.subTitle,w=o.extra,W=o.tip,N=o.wrap,Ae=N===void 0?!1:N,Pe=o.layout,re=o.loading,he=o.gutter,Se=he===void 0?0:he,je=o.tooltip,ce=o.split,Te=o.headerBordered,Be=Te===void 0?!1:Te,ge=o.bordered,Ge=ge===void 0?!1:ge,ee=o.children,oe=o.size,J=o.actions,ke=o.ghost,Ye=ke===void 0?!1:ke,we=o.hoverable,qe=we===void 0?!1:we,_e=o.direction,Fe=o.collapsed,Ue=o.collapsible,et=Ue===void 0?!1:Ue,Ve=o.collapsibleIconRender,We=o.defaultCollapsed,tt=We===void 0?!1:We,at=o.onCollapse,nt=o.checked,Le=o.onChecked,Re=o.tabs,De=o.type,xe=(0,de.Z)(o,m),lt=(0,t.useContext)(M.ZP.ConfigContext),rt=lt.getPrefixCls,He=g(),ct=(0,Y.Z)(tt,{value:Fe,onChange:at}),Je=(0,se.Z)(ct,2),Oe=Je[0],ot=Je[1],$e=["xxl","xl","lg","md","sm","xs"],it=function(G){var S=[0,0],F=Array.isArray(G)?G:[G,0];return F.forEach(function(H,ie){if((0,s.Z)(H)==="object")for(var te=0;te<$e.length;te+=1){var Ee=$e[te];if(He[Ee]&&H[Ee]!==void 0){S[ie]=H[Ee];break}}else S[ie]=H||0}),S},Ze=function(G,S){return G?S:{}},st=function(G){var S=G;if((0,s.Z)(G)==="object")for(var F=0;F<$e.length;F+=1){var H=$e[F];if(He[H]&&G[H]!==void 0){S=G[H];break}}var ie=Ze(typeof S=="string"&&/\d%|\dpx/i.test(S),{width:S,flexShrink:0});return{span:S,colSpanStyle:ie}},C=rt("pro-card"),dt=it(Se),Qe=(0,se.Z)(dt,2),ve=Qe[0],me=Qe[1],Ie=!1,Ke=t.Children.toArray(ee),vt=Ke.map(function(I,G){var S;if(!(I==null||(S=I.type)===null||S===void 0)&&S.isProCard){var F;Ie=!0;var H=I.props.colSpan,ie=st(H),te=ie.span,Ee=ie.colSpanStyle,ft=U()(["".concat(C,"-col")],(F={},(0,c.Z)(F,"".concat(C,"-split-vertical"),ce==="vertical"&&G!==Ke.length-1),(0,c.Z)(F,"".concat(C,"-split-horizontal"),ce==="horizontal"&&G!==Ke.length-1),(0,c.Z)(F,"".concat(C,"-col-").concat(te),typeof te=="number"&&te>=0&&te<=24),F));return t.createElement("div",{style:(0,D.Z)((0,D.Z)((0,D.Z)({},Ee),Ze(ve>0,{paddingRight:ve/2,paddingLeft:ve/2})),Ze(me>0,{paddingTop:me/2,paddingBottom:me/2})),key:"pro-card-col-".concat(I?.key||G),className:ft},t.cloneElement(I))}return I}),mt=U()("".concat(C),x,(v={},(0,c.Z)(v,"".concat(C,"-border"),Ge),(0,c.Z)(v,"".concat(C,"-contain-card"),Ie),(0,c.Z)(v,"".concat(C,"-loading"),re),(0,c.Z)(v,"".concat(C,"-split"),ce==="vertical"||ce==="horizontal"),(0,c.Z)(v,"".concat(C,"-ghost"),Ye),(0,c.Z)(v,"".concat(C,"-hoverable"),qe),(0,c.Z)(v,"".concat(C,"-size-").concat(oe),oe),(0,c.Z)(v,"".concat(C,"-type-").concat(De),De),(0,c.Z)(v,"".concat(C,"-collapse"),Oe),(0,c.Z)(v,"".concat(C,"-checked"),nt),v)),ut=U()("".concat(C,"-body"),(u={},(0,c.Z)(u,"".concat(C,"-body-center"),Pe==="center"),(0,c.Z)(u,"".concat(C,"-body-direction-column"),ce==="horizontal"||_e==="column"),(0,c.Z)(u,"".concat(C,"-body-wrap"),Ae&&Ie),u)),yt=(0,D.Z)((0,D.Z)((0,D.Z)({},Ze(ve>0,{marginRight:-ve/2,marginLeft:-ve/2})),Ze(me>0,{marginTop:-me/2,marginBottom:-me/2})),L),Xe=t.isValidElement(re)?re:t.createElement(i,{prefix:C,style:L.padding===0||L.padding==="0px"?{padding:24}:void 0}),ze=et&&Fe===void 0&&(Ve?Ve({collapsed:Oe}):t.createElement(ae.Z,{rotate:Oe?void 0:90,className:"".concat(C,"-collapsible-icon")}));return t.createElement("div",(0,Z.Z)({className:mt,style:B,ref:y,onClick:function(G){var S;Le?.(G),xe==null||(S=xe.onClick)===null||S===void 0||S.call(xe,G)}},(0,le.Z)(xe,["prefixCls","colSpan"])),(q||w||ze)&&t.createElement("div",{className:U()("".concat(C,"-header"),(P={},(0,c.Z)(P,"".concat(C,"-header-border"),Be||De==="inner"),(0,c.Z)(P,"".concat(C,"-header-collapsible"),ze),P)),style:k,onClick:function(){ze&&ot(!Oe)}},t.createElement("div",{className:"".concat(C,"-title")},ze,t.createElement(ne.Z,{label:q,tooltip:je||W,subTitle:_})),w&&t.createElement("div",{className:"".concat(C,"-extra")},w)),Re?t.createElement("div",{className:"".concat(C,"-tabs")},t.createElement(Q.Z,(0,Z.Z)({onChange:Re.onChange},Re),re?Xe:ee)):t.createElement("div",{className:ut,style:yt},re?Xe:vt),t.createElement(a,{actions:J,prefixCls:C}))}),d=b,A=function(y){var v=(0,t.useContext)(M.ZP.ConfigContext),u=v.getPrefixCls,P=u("pro-card-divider"),x=y.className,B=y.style,R=B===void 0?{}:B,L=y.type,K=U()(P,x,(0,c.Z)({},"".concat(P,"-").concat(L),L));return t.createElement("div",{className:K,style:R})},O=A,j=["key","tab","tabKey","disabled","destroyInactiveTabPane","children","className","style","cardProps"],E=function(y){var v=y.key,u=y.tab,P=y.tabKey,x=y.disabled,B=y.destroyInactiveTabPane,R=y.children,L=y.className,K=y.style,k=y.cardProps,q=(0,de.Z)(y,j),_=(0,t.useContext)(M.ZP.ConfigContext),w=_.getPrefixCls,W=w("pro-card-tabpane"),N=U()(W,L);return t.createElement(Q.Z.TabPane,(0,Z.Z)({key:v,tabKey:P,tab:u,className:N,style:K,disabled:x,destroyInactiveTabPane:B},q),t.createElement(d,k,R))},$=E,V=function(y){return t.createElement(d,(0,Z.Z)({bodyStyle:{padding:0}},y))},z=d;z.isProCard=!0,z.Divider=O,z.TabPane=$,z.Group=V;var p=z},91894:function(Me,ue,l){l.d(ue,{Z:function(){return Ce}});var Z=l(4942),t=l(87462),ye=l(94184),Q=l.n(ye),D=l(98423),c=l(67294),s=l(53124),se=l(97647),pe=l(90860),M=l(51752),de=function(a,h){var f={};for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&h.indexOf(n)<0&&(f[n]=a[n]);if(a!=null&&typeof Object.getOwnPropertySymbols=="function")for(var e=0,n=Object.getOwnPropertySymbols(a);e<n.length;e++)h.indexOf(n[e])<0&&Object.prototype.propertyIsEnumerable.call(a,n[e])&&(f[n[e]]=a[n[e]]);return f},Ne=function(h){var f=h.prefixCls,n=h.className,e=h.hoverable,r=e===void 0?!0:e,i=de(h,["prefixCls","className","hoverable"]);return c.createElement(s.C,null,function(m){var g=m.getPrefixCls,b=g("card",f),d=Q()("".concat(b,"-grid"),n,(0,Z.Z)({},"".concat(b,"-grid-hoverable"),r));return c.createElement("div",(0,t.Z)({},i,{className:d}))})},T=Ne,X=function(a,h){var f={};for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&h.indexOf(n)<0&&(f[n]=a[n]);if(a!=null&&typeof Object.getOwnPropertySymbols=="function")for(var e=0,n=Object.getOwnPropertySymbols(a);e<n.length;e++)h.indexOf(n[e])<0&&Object.prototype.propertyIsEnumerable.call(a,n[e])&&(f[n[e]]=a[n[e]]);return f};function fe(a){var h=a.map(function(f,n){return c.createElement("li",{style:{width:"".concat(100/a.length,"%")},key:"action-".concat(n)},c.createElement("span",null,f))});return h}var ae=c.forwardRef(function(a,h){var f,n,e=c.useContext(s.E_),r=e.getPrefixCls,i=e.direction,m=c.useContext(se.Z),g=function(oe){var J;(J=a.onTabChange)===null||J===void 0||J.call(a,oe)},b=function(){var oe;return c.Children.forEach(a.children,function(J){J&&J.type&&J.type===T&&(oe=!0)}),oe},d=a.prefixCls,A=a.className,O=a.extra,j=a.headStyle,E=j===void 0?{}:j,$=a.bodyStyle,V=$===void 0?{}:$,z=a.title,p=a.loading,o=a.bordered,y=o===void 0?!0:o,v=a.size,u=a.type,P=a.cover,x=a.actions,B=a.tabList,R=a.children,L=a.activeTabKey,K=a.defaultActiveTabKey,k=a.tabBarExtraContent,q=a.hoverable,_=a.tabProps,w=_===void 0?{}:_,W=X(a,["prefixCls","className","extra","headStyle","bodyStyle","title","loading","bordered","size","type","cover","actions","tabList","children","activeTabKey","defaultActiveTabKey","tabBarExtraContent","hoverable","tabProps"]),N=r("card",d),Ae=c.createElement(pe.Z,{loading:!0,active:!0,paragraph:{rows:4},title:!1},R),Pe=L!==void 0,re=(0,t.Z)((0,t.Z)({},w),(f={},(0,Z.Z)(f,Pe?"activeKey":"defaultActiveKey",Pe?L:K),(0,Z.Z)(f,"tabBarExtraContent",k),f)),he,Se=B&&B.length?c.createElement(M.Z,(0,t.Z)({size:"large"},re,{className:"".concat(N,"-head-tabs"),onChange:g}),B.map(function(ee){return c.createElement(M.Z.TabPane,{tab:ee.tab,disabled:ee.disabled,key:ee.key})})):null;(z||O||Se)&&(he=c.createElement("div",{className:"".concat(N,"-head"),style:E},c.createElement("div",{className:"".concat(N,"-head-wrapper")},z&&c.createElement("div",{className:"".concat(N,"-head-title")},z),O&&c.createElement("div",{className:"".concat(N,"-extra")},O)),Se));var je=P?c.createElement("div",{className:"".concat(N,"-cover")},P):null,ce=c.createElement("div",{className:"".concat(N,"-body"),style:V},p?Ae:R),Te=x&&x.length?c.createElement("ul",{className:"".concat(N,"-actions")},fe(x)):null,Be=(0,D.Z)(W,["onTabChange"]),ge=v||m,Ge=Q()(N,(n={},(0,Z.Z)(n,"".concat(N,"-loading"),p),(0,Z.Z)(n,"".concat(N,"-bordered"),y),(0,Z.Z)(n,"".concat(N,"-hoverable"),q),(0,Z.Z)(n,"".concat(N,"-contain-grid"),b()),(0,Z.Z)(n,"".concat(N,"-contain-tabs"),B&&B.length),(0,Z.Z)(n,"".concat(N,"-").concat(ge),ge),(0,Z.Z)(n,"".concat(N,"-type-").concat(u),!!u),(0,Z.Z)(n,"".concat(N,"-rtl"),i==="rtl"),n),A);return c.createElement("div",(0,t.Z)({ref:h},Be,{className:Ge}),he,je,ce,Te)}),ne=ae,be=function(a,h){var f={};for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&h.indexOf(n)<0&&(f[n]=a[n]);if(a!=null&&typeof Object.getOwnPropertySymbols=="function")for(var e=0,n=Object.getOwnPropertySymbols(a);e<n.length;e++)h.indexOf(n[e])<0&&Object.prototype.propertyIsEnumerable.call(a,n[e])&&(f[n[e]]=a[n[e]]);return f},U=function(h){return c.createElement(s.C,null,function(f){var n=f.getPrefixCls,e=h.prefixCls,r=h.className,i=h.avatar,m=h.title,g=h.description,b=be(h,["prefixCls","className","avatar","title","description"]),d=n("card",e),A=Q()("".concat(d,"-meta"),r),O=i?c.createElement("div",{className:"".concat(d,"-meta-avatar")},i):null,j=m?c.createElement("div",{className:"".concat(d,"-meta-title")},m):null,E=g?c.createElement("div",{className:"".concat(d,"-meta-description")},g):null,$=j||E?c.createElement("div",{className:"".concat(d,"-meta-detail")},j,E):null;return c.createElement("div",(0,t.Z)({},b,{className:A}),O,$)})},le=U,Y=ne;Y.Grid=T,Y.Meta=le;var Ce=Y},4914:function(Me,ue,l){l.d(ue,{K:function(){return le},Z:function(){return n}});var Z=l(4942),t=l(29439),ye=l(71002),Q=l(94184),D=l.n(Q),c=l(50344),s=l(67294),se=l(53124),pe=l(96159),M=l(24308),de=function(r){var i=r.children;return i},Ne=de,T=l(87462);function X(e){return e!=null}var fe=function(r){var i=r.itemPrefixCls,m=r.component,g=r.span,b=r.className,d=r.style,A=r.labelStyle,O=r.contentStyle,j=r.bordered,E=r.label,$=r.content,V=r.colon,z=m;if(j){var p;return s.createElement(z,{className:D()((p={},(0,Z.Z)(p,"".concat(i,"-item-label"),X(E)),(0,Z.Z)(p,"".concat(i,"-item-content"),X($)),p),b),style:d,colSpan:g},X(E)&&s.createElement("span",{style:A},E),X($)&&s.createElement("span",{style:O},$))}return s.createElement(z,{className:D()("".concat(i,"-item"),b),style:d,colSpan:g},s.createElement("div",{className:"".concat(i,"-item-container")},(E||E===0)&&s.createElement("span",{className:D()("".concat(i,"-item-label"),(0,Z.Z)({},"".concat(i,"-item-no-colon"),!V)),style:A},E),($||$===0)&&s.createElement("span",{className:D()("".concat(i,"-item-content")),style:O},$)))},ae=fe;function ne(e,r,i){var m=r.colon,g=r.prefixCls,b=r.bordered,d=i.component,A=i.type,O=i.showLabel,j=i.showContent,E=i.labelStyle,$=i.contentStyle;return e.map(function(V,z){var p=V.props,o=p.label,y=p.children,v=p.prefixCls,u=v===void 0?g:v,P=p.className,x=p.style,B=p.labelStyle,R=p.contentStyle,L=p.span,K=L===void 0?1:L,k=V.key;return typeof d=="string"?s.createElement(ae,{key:"".concat(A,"-").concat(k||z),className:P,style:x,labelStyle:(0,T.Z)((0,T.Z)({},E),B),contentStyle:(0,T.Z)((0,T.Z)({},$),R),span:K,colon:m,component:d,itemPrefixCls:u,bordered:b,label:O?o:null,content:j?y:null}):[s.createElement(ae,{key:"label-".concat(k||z),className:P,style:(0,T.Z)((0,T.Z)((0,T.Z)({},E),x),B),span:1,colon:m,component:d[0],itemPrefixCls:u,bordered:b,label:o}),s.createElement(ae,{key:"content-".concat(k||z),className:P,style:(0,T.Z)((0,T.Z)((0,T.Z)({},$),x),R),span:K*2-1,component:d[1],itemPrefixCls:u,bordered:b,content:y})]})}var be=function(r){var i=s.useContext(le),m=r.prefixCls,g=r.vertical,b=r.row,d=r.index,A=r.bordered;return g?s.createElement(s.Fragment,null,s.createElement("tr",{key:"label-".concat(d),className:"".concat(m,"-row")},ne(b,r,(0,T.Z)({component:"th",type:"label",showLabel:!0},i))),s.createElement("tr",{key:"content-".concat(d),className:"".concat(m,"-row")},ne(b,r,(0,T.Z)({component:"td",type:"content",showContent:!0},i)))):s.createElement("tr",{key:d,className:"".concat(m,"-row")},ne(b,r,(0,T.Z)({component:A?["th","td"]:"td",type:"item",showLabel:!0,showContent:!0},i)))},U=be,le=s.createContext({}),Y={xxl:3,xl:3,lg:3,md:3,sm:2,xs:1};function Ce(e,r){if(typeof e=="number")return e;if((0,ye.Z)(e)==="object")for(var i=0;i<M.c4.length;i++){var m=M.c4[i];if(r[m]&&e[m]!==void 0)return e[m]||Y[m]}return 3}function a(e,r,i){var m=e;return(r===void 0||r>i)&&(m=(0,pe.Tm)(e,{span:i})),m}function h(e,r){var i=(0,c.Z)(e).filter(function(d){return d}),m=[],g=[],b=r;return i.forEach(function(d,A){var O,j=(O=d.props)===null||O===void 0?void 0:O.span,E=j||1;if(A===i.length-1){g.push(a(d,j,b)),m.push(g);return}E<b?(b-=E,g.push(d)):(g.push(a(d,E,b)),m.push(g),b=r,g=[])}),m}function f(e){var r,i=e.prefixCls,m=e.title,g=e.extra,b=e.column,d=b===void 0?Y:b,A=e.colon,O=A===void 0?!0:A,j=e.bordered,E=e.layout,$=e.children,V=e.className,z=e.style,p=e.size,o=e.labelStyle,y=e.contentStyle,v=s.useContext(se.E_),u=v.getPrefixCls,P=v.direction,x=u("descriptions",i),B=s.useState({}),R=(0,t.Z)(B,2),L=R[0],K=R[1],k=Ce(d,L);s.useEffect(function(){var w=M.ZP.subscribe(function(W){(0,ye.Z)(d)==="object"&&K(W)});return function(){M.ZP.unsubscribe(w)}},[]);var q=h($,k),_=s.useMemo(function(){return{labelStyle:o,contentStyle:y}},[o,y]);return s.createElement(le.Provider,{value:_},s.createElement("div",{className:D()(x,(r={},(0,Z.Z)(r,"".concat(x,"-").concat(p),p&&p!=="default"),(0,Z.Z)(r,"".concat(x,"-bordered"),!!j),(0,Z.Z)(r,"".concat(x,"-rtl"),P==="rtl"),r),V),style:z},(m||g)&&s.createElement("div",{className:"".concat(x,"-header")},m&&s.createElement("div",{className:"".concat(x,"-title")},m),g&&s.createElement("div",{className:"".concat(x,"-extra")},g)),s.createElement("div",{className:"".concat(x,"-view")},s.createElement("table",null,s.createElement("tbody",null,q.map(function(w,W){return s.createElement(U,{key:W,index:W,colon:O,prefixCls:x,vertical:E==="vertical",bordered:j,row:w})}))))))}f.Item=Ne;var n=f}}]);
