define(["./when-54335d57","./Cartesian2-8646c5a1","./ArcType-2b58731c","./arrayRemoveDuplicates-3fea1e5f","./Transforms-79117a7b","./Color-98c5f877","./ComponentDatatype-1a100acd","./Check-24483042","./GeometryAttribute-374f805d","./GeometryAttributes-caa08d6c","./IndexDatatype-82ceea78","./Math-d6182036","./PolylinePipeline-3803a6c2","./VertexFormat-81ec7207","./RuntimeError-88a32665","./WebGLConstants-95ceb4e9","./EllipsoidGeodesic-cc216670","./EllipsoidRhumbLine-2b7999f3","./IntersectionTests-5394f658","./Plane-13ae4b1b"],function(Y, q, z, J, j, K, Q, e, X, Z, $, ee, te, y, t, r, a, o, n, i){"use strict";var re=[];function m(e){var t=(e=Y.defaultValue(e,Y.defaultValue.EMPTY_OBJECT)).positions,r=e.colors,a=Y.defaultValue(e.width,1),o=Y.defaultValue(e.colorsPerVertex,!1);this._positions=t,this._colors=r,this._width=a,this._colorsPerVertex=o,this._vertexFormat=y.VertexFormat.clone(Y.defaultValue(e.vertexFormat,y.VertexFormat.DEFAULT)),this._arcType=Y.defaultValue(e.arcType,z.ArcType.GEODESIC),this._granularity=Y.defaultValue(e.granularity,ee.CesiumMath.RADIANS_PER_DEGREE),this._ellipsoid=q.Ellipsoid.clone(Y.defaultValue(e.ellipsoid,q.Ellipsoid.WGS84)),this._workerName="createPolylineGeometry";t=1+t.length*q.Cartesian3.packedLength;t+=Y.defined(r)?1+r.length*K.Color.packedLength:1,this.packedLength=t+q.Ellipsoid.packedLength+y.VertexFormat.packedLength+4}m.pack=function(e, t, r){var a;r=Y.defaultValue(r,0);var o=e._positions,n=o.length;for(t[r++]=n,a=0; a<n; ++a,r+=q.Cartesian3.packedLength)q.Cartesian3.pack(o[a],t,r);var i=e._colors,n=Y.defined(i)?i.length:0;for(t[r++]=n,a=0; a<n; ++a,r+=K.Color.packedLength)K.Color.pack(i[a],t,r);return q.Ellipsoid.pack(e._ellipsoid,t,r),r+=q.Ellipsoid.packedLength,y.VertexFormat.pack(e._vertexFormat,t,r),r+=y.VertexFormat.packedLength,t[r++]=e._width,t[r++]=e._colorsPerVertex?1:0,t[r++]=e._arcType,t[r]=e._granularity,t};var h=q.Ellipsoid.clone(q.Ellipsoid.UNIT_SPHERE),f=new y.VertexFormat,C={positions:void 0,colors:void 0,ellipsoid:h,vertexFormat:f,width:void 0,colorsPerVertex:void 0,arcType:void 0,granularity:void 0};m.unpack=function(e, t, r){t=Y.defaultValue(t,0);for(var a=e[t++],o=new Array(a),n=0; n<a; ++n,t+=q.Cartesian3.packedLength)o[n]=q.Cartesian3.unpack(e,t);var i=0<(a=e[t++])?new Array(a):void 0;for(n=0; n<a; ++n,t+=K.Color.packedLength)i[n]=K.Color.unpack(e,t);var l=q.Ellipsoid.unpack(e,t,h);t+=q.Ellipsoid.packedLength;var s=y.VertexFormat.unpack(e,t,f);t+=y.VertexFormat.packedLength;var p=e[t++],c=1===e[t++],d=e[t++],u=e[t];return Y.defined(r)?(r._positions=o,r._colors=i,r._ellipsoid=q.Ellipsoid.clone(l,r._ellipsoid),r._vertexFormat=y.VertexFormat.clone(s,r._vertexFormat),r._width=p,r._colorsPerVertex=c,r._arcType=d,r._granularity=u,r):(C.positions=o,C.colors=i,C.width=p,C.colorsPerVertex=c,C.arcType=d,C.granularity=u,new m(C))};var ae=new q.Cartesian3,oe=new q.Cartesian3,ne=new q.Cartesian3,ie=new q.Cartesian3;return m.createGeometry=function(e){var t=e._width,r=e._vertexFormat,a=e._colors,o=e._colorsPerVertex,n=e._arcType,i=e._granularity,l=e._ellipsoid,s=J.arrayRemoveDuplicates(e._positions,q.Cartesian3.equalsEpsilon);if(!((P=s.length)<2||t<=0)){if(n===z.ArcType.GEODESIC||n===z.ArcType.RHUMB){var p,c=n===z.ArcType.GEODESIC?(p=ee.CesiumMath.chordLength(i,l.maximumRadius),te.PolylinePipeline.numberOfPoints):(p=i,te.PolylinePipeline.numberOfPointsRhumbLine),d=te.PolylinePipeline.extractHeights(s,l);if(Y.defined(a)){for(var u=1,y=0; y<P-1; ++y)u+=c(s[y],s[y+1],p);var m=new Array(u),h=0;for(y=0; y<P-1; ++y){var f=s[y],C=s[y+1],v=a[y],g=c(f,C,p);if(o&&y<u)for(var _=function(e, t, r){var a=re;a.length=r;var o=e.red,n=e.green,i=e.blue,l=e.alpha,s=t.red,p=t.green,c=t.blue,d=t.alpha;if(K.Color.equals(e,t)){for(f=0; f<r; f++)a[f]=K.Color.clone(e);return a}for(var u=(s-o)/r,y=(p-n)/r,m=(c-i)/r,h=(d-l)/r,f=0; f<r; f++)a[f]=new K.Color(o+f*u,n+f*y,i+f*m,l+f*h);return a}(v,a[y+1],g),A=_.length,E=0; E<A; ++E)m[h++]=_[E];else for(E=0; E<g; ++E)m[h++]=K.Color.clone(v)}m[h]=K.Color.clone(a[a.length-1]),a=m,re.length=0}s=n===z.ArcType.GEODESIC?te.PolylinePipeline.generateCartesianArc({positions:s,minDistance:p,ellipsoid:l,height:d}):te.PolylinePipeline.generateCartesianRhumbArc({positions:s,granularity:p,ellipsoid:l,height:d})}var P,b,w,T,l=4*(P=s.length)-4,x=new Float64Array(3*l),k=new Float64Array(3*l),D=new Float64Array(3*l),V=new Float32Array(2*l),L=r.st?new Float32Array(2*l):void 0,F=Y.defined(a)?new Uint8Array(4*l):void 0,G=0,O=0,R=0,I=0;for(E=0; E<P; ++E){0===E?(q.Cartesian3.subtract(s[0],s[1],b=ae),q.Cartesian3.add(s[0],b,b)):b=s[E-1],q.Cartesian3.clone(b,ne),q.Cartesian3.clone(s[E],oe),E===P-1?(q.Cartesian3.subtract(s[P-1],s[P-2],b=ae),q.Cartesian3.add(s[P-1],b,b)):b=s[E+1],q.Cartesian3.clone(b,ie),Y.defined(F)&&(w=0===E||o?a[E]:a[E-1],E!==P-1&&(T=a[E]));for(var S=E===P-1?2:4,B=0===E?2:0; B<S; ++B){q.Cartesian3.pack(oe,x,G),q.Cartesian3.pack(ne,k,G),q.Cartesian3.pack(ie,D,G),G+=3;var U=B-2<0?-1:1;V[O++]=B%2*2-1,V[O++]=U*t,r.st&&(L[R++]=E/(P-1),L[R++]=Math.max(V[O-2],0)),Y.defined(F)&&(F[I++]=K.Color.floatToByte((U=B<2?w:T).red),F[I++]=K.Color.floatToByte(U.green),F[I++]=K.Color.floatToByte(U.blue),F[I++]=K.Color.floatToByte(U.alpha))}}d=new Z.GeometryAttributes;d.position=new X.GeometryAttribute({componentDatatype:Q.ComponentDatatype.DOUBLE,componentsPerAttribute:3,values:x}),d.prevPosition=new X.GeometryAttribute({componentDatatype:Q.ComponentDatatype.DOUBLE,componentsPerAttribute:3,values:k}),d.nextPosition=new X.GeometryAttribute({componentDatatype:Q.ComponentDatatype.DOUBLE,componentsPerAttribute:3,values:D}),d.expandAndWidth=new X.GeometryAttribute({componentDatatype:Q.ComponentDatatype.FLOAT,componentsPerAttribute:2,values:V}),r.st&&(d.st=new X.GeometryAttribute({componentDatatype:Q.ComponentDatatype.FLOAT,componentsPerAttribute:2,values:L})),Y.defined(F)&&(d.color=new X.GeometryAttribute({componentDatatype:Q.ComponentDatatype.UNSIGNED_BYTE,componentsPerAttribute:4,values:F,normalize:!0}));var N=$.IndexDatatype.createTypedArray(l,6*P-6),M=0,H=0,W=P-1;for(E=0; E<W; ++E)N[H++]=M,N[H++]=M+2,N[H++]=M+1,N[H++]=M+1,N[H++]=M+2,N[H++]=M+3,M+=4;return new X.Geometry({attributes:d,indices:N,primitiveType:X.PrimitiveType.TRIANGLES,boundingSphere:j.BoundingSphere.fromPoints(s),geometryType:X.GeometryType.POLYLINES})}},function(e, t){return(e=Y.defined(t)?m.unpack(e,t):e)._ellipsoid=q.Ellipsoid.clone(e._ellipsoid),m.createGeometry(e)}});
