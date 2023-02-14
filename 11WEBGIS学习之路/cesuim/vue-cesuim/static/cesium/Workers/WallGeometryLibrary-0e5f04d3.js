define(["exports","./arrayRemoveDuplicates-3fea1e5f","./Cartesian2-8646c5a1","./when-54335d57","./Math-d6182036","./PolylinePipeline-3803a6c2"],function(e, f, A, C, w, P){"use strict";var i={};var M=new A.Cartographic,b=new A.Cartographic;function E(e, i, t, a){var r=(i=f.arrayRemoveDuplicates(i,A.Cartesian3.equalsEpsilon)).length;if(!(r<2)){var n=C.defined(a),o=C.defined(t),l=new Array(r),s=new Array(r),h=new Array(r),g=i[0];l[0]=g;var p=e.cartesianToCartographic(g,M);o&&(p.height=t[0]),s[0]=p.height,h[0]=n?a[0]:0;for(var u,c,d=s[0]===h[0],v=1,y=1; y<r; ++y){var m=i[y],P=e.cartesianToCartographic(m,b);o&&(P.height=t[y]),d=d&&0===P.height,u=p,c=P,w.CesiumMath.equalsEpsilon(u.latitude,c.latitude,w.CesiumMath.EPSILON10)&&w.CesiumMath.equalsEpsilon(u.longitude,c.longitude,w.CesiumMath.EPSILON10)?p.height<P.height&&(s[v-1]=P.height):(l[v]=m,s[v]=P.height,h[v]=n?a[y]:0,d=d&&s[v]===h[v],A.Cartographic.clone(P,p),++v)}if(!(d||v<2))return l.length=v,s.length=v,h.length=v,{positions:l,topHeights:s,bottomHeights:h}}}var F=new Array(2),H=new Array(2),L={positions:void 0,height:void 0,granularity:void 0,ellipsoid:void 0};i.computePositions=function(e, i, t, a, r, n){var o=E(e,i,t,a);if(C.defined(o)){i=o.positions,t=o.topHeights,a=o.bottomHeights;var l=i.length,o=l-2,s=w.CesiumMath.chordLength(r,e.maximumRadius),h=L;if(h.minDistance=s,h.ellipsoid=e,n){for(var g=0,p=0; p<l-1; p++)g+=P.PolylinePipeline.numberOfPoints(i[p],i[p+1],s)+1;var u=new Float64Array(3*g),c=new Float64Array(3*g),d=F,v=H;h.positions=d,h.height=v;var y=0;for(p=0; p<l-1; p++){d[0]=i[p],d[1]=i[p+1],v[0]=t[p],v[1]=t[p+1];var m=P.PolylinePipeline.generateArc(h);u.set(m,y),v[0]=a[p],v[1]=a[p+1],c.set(P.PolylinePipeline.generateArc(h),y),y+=m.length}}else h.positions=i,h.height=t,u=new Float64Array(P.PolylinePipeline.generateArc(h)),h.height=a,c=new Float64Array(P.PolylinePipeline.generateArc(h));return{bottomPositions:c,topPositions:u,numCorners:o}}},e.WallGeometryLibrary=i});
