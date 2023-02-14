define(["./when-54335d57","./Cartesian2-8646c5a1","./ArcType-2b58731c","./Transforms-79117a7b","./Color-98c5f877","./ComponentDatatype-1a100acd","./Check-24483042","./GeometryAttribute-374f805d","./GeometryAttributes-caa08d6c","./IndexDatatype-82ceea78","./Math-d6182036","./PolylinePipeline-3803a6c2","./RuntimeError-88a32665","./WebGLConstants-95ceb4e9","./EllipsoidGeodesic-cc216670","./EllipsoidRhumbLine-2b7999f3","./IntersectionTests-5394f658","./Plane-13ae4b1b"],function(L, V, x, S, I, R, e, O, M, U, N, F, o, t, r, a, l, i){"use strict";function d(e){var o=(e=L.defaultValue(e,L.defaultValue.EMPTY_OBJECT)).positions,t=e.colors,r=L.defaultValue(e.colorsPerVertex,!1);this._positions=o,this._colors=t,this._colorsPerVertex=r,this._arcType=L.defaultValue(e.arcType,x.ArcType.GEODESIC),this._granularity=L.defaultValue(e.granularity,N.CesiumMath.RADIANS_PER_DEGREE),this._ellipsoid=L.defaultValue(e.ellipsoid,V.Ellipsoid.WGS84),this._workerName="createSimplePolylineGeometry";o=1+o.length*V.Cartesian3.packedLength;o+=L.defined(t)?1+t.length*I.Color.packedLength:1,this.packedLength=o+V.Ellipsoid.packedLength+3}d.pack=function(e, o, t){var r;t=L.defaultValue(t,0);var a=e._positions,l=a.length;for(o[t++]=l,r=0; r<l; ++r,t+=V.Cartesian3.packedLength)V.Cartesian3.pack(a[r],o,t);var i=e._colors,l=L.defined(i)?i.length:0;for(o[t++]=l,r=0; r<l; ++r,t+=I.Color.packedLength)I.Color.pack(i[r],o,t);return V.Ellipsoid.pack(e._ellipsoid,o,t),t+=V.Ellipsoid.packedLength,o[t++]=e._colorsPerVertex?1:0,o[t++]=e._arcType,o[t]=e._granularity,o},d.unpack=function(e, o, t){o=L.defaultValue(o,0);for(var r=e[o++],a=new Array(r),l=0; l<r; ++l,o+=V.Cartesian3.packedLength)a[l]=V.Cartesian3.unpack(e,o);var i=0<(r=e[o++])?new Array(r):void 0;for(l=0; l<r; ++l,o+=I.Color.packedLength)i[l]=I.Color.unpack(e,o);var n=V.Ellipsoid.unpack(e,o);o+=V.Ellipsoid.packedLength;var s=1===e[o++],p=e[o++],c=e[o];return L.defined(t)?(t._positions=a,t._colors=i,t._ellipsoid=n,t._colorsPerVertex=s,t._arcType=p,t._granularity=c,t):new d({positions:a,colors:i,ellipsoid:n,colorsPerVertex:s,arcType:p,granularity:c})};var H=new Array(2),W=new Array(2),Y={positions:H,height:W,ellipsoid:void 0,minDistance:void 0,granularity:void 0};return d.createGeometry=function(e){var o=e._positions,t=e._colors,r=e._colorsPerVertex,a=e._arcType,l=e._granularity,e=e._ellipsoid,i=N.CesiumMath.chordLength(l,e.maximumRadius),n=L.defined(t)&&!r,s=o.length,p=0;if(a===x.ArcType.GEODESIC||a===x.ArcType.RHUMB){var c,d,y=a===x.ArcType.GEODESIC?(c=N.CesiumMath.chordLength(l,e.maximumRadius),d=F.PolylinePipeline.numberOfPoints,F.PolylinePipeline.generateArc):(c=l,d=F.PolylinePipeline.numberOfPointsRhumbLine,F.PolylinePipeline.generateRhumbArc),f=F.PolylinePipeline.extractHeights(o,e),u=Y;if(a===x.ArcType.GEODESIC?u.minDistance=i:u.granularity=l,u.ellipsoid=e,n){for(var h=0,C=0; C<s-1; C++)h+=d(o[C],o[C+1],c)+1;B=new Float64Array(3*h),A=new Uint8Array(4*h),u.positions=H,u.height=W;var T=0;for(C=0; C<s-1; ++C){H[0]=o[C],H[1]=o[C+1],W[0]=f[C],W[1]=f[C+1];var g=y(u);if(L.defined(t))for(var m=g.length/3,P=t[C],_=0; _<m; ++_)A[T++]=I.Color.floatToByte(P.red),A[T++]=I.Color.floatToByte(P.green),A[T++]=I.Color.floatToByte(P.blue),A[T++]=I.Color.floatToByte(P.alpha);B.set(g,p),p+=g.length}}else if(u.positions=o,u.height=f,B=new Float64Array(y(u)),L.defined(t)){for(A=new Uint8Array(B.length/3*4),C=0; C<s-1; ++C)p=function(e, o, t, r, a, l, i){var n=F.PolylinePipeline.numberOfPoints(e,o,a),s=t.red,p=t.green,c=t.blue,d=t.alpha,y=r.red,e=r.green,o=r.blue,a=r.alpha;if(I.Color.equals(t,r)){for(g=0; g<n; g++)l[i++]=I.Color.floatToByte(s),l[i++]=I.Color.floatToByte(p),l[i++]=I.Color.floatToByte(c),l[i++]=I.Color.floatToByte(d);return i}for(var f=(y-s)/n,u=(e-p)/n,h=(o-c)/n,C=(a-d)/n,T=i,g=0; g<n; g++)l[T++]=I.Color.floatToByte(s+g*f),l[T++]=I.Color.floatToByte(p+g*u),l[T++]=I.Color.floatToByte(c+g*h),l[T++]=I.Color.floatToByte(d+g*C);return T}(o[C],o[C+1],t[C],t[C+1],i,A,p);var v=t[s-1];A[p++]=I.Color.floatToByte(v.red),A[p++]=I.Color.floatToByte(v.green),A[p++]=I.Color.floatToByte(v.blue),A[p++]=I.Color.floatToByte(v.alpha)}}else{var b=n?2*s-2:s,B=new Float64Array(3*b),A=L.defined(t)?new Uint8Array(4*b):void 0,E=0,k=0;for(C=0; C<s; ++C){var G=o[C];if(n&&0<C&&(V.Cartesian3.pack(G,B,E),E+=3,P=t[C-1],A[k++]=I.Color.floatToByte(P.red),A[k++]=I.Color.floatToByte(P.green),A[k++]=I.Color.floatToByte(P.blue),A[k++]=I.Color.floatToByte(P.alpha)),n&&C===s-1)break;V.Cartesian3.pack(G,B,E),E+=3,L.defined(t)&&(P=t[C],A[k++]=I.Color.floatToByte(P.red),A[k++]=I.Color.floatToByte(P.green),A[k++]=I.Color.floatToByte(P.blue),A[k++]=I.Color.floatToByte(P.alpha))}}v=new M.GeometryAttributes;v.position=new O.GeometryAttribute({componentDatatype:R.ComponentDatatype.DOUBLE,componentsPerAttribute:3,values:B}),L.defined(t)&&(v.color=new O.GeometryAttribute({componentDatatype:R.ComponentDatatype.UNSIGNED_BYTE,componentsPerAttribute:4,values:A,normalize:!0})),b=B.length/3;var w=U.IndexDatatype.createTypedArray(b,2*(b-1)),D=0;for(C=0; C<b-1; ++C)w[D++]=C,w[D++]=C+1;return new O.Geometry({attributes:v,indices:w,primitiveType:O.PrimitiveType.LINES,boundingSphere:S.BoundingSphere.fromPoints(o)})},function(e, o){return(e=L.defined(o)?d.unpack(e,o):e)._ellipsoid=V.Ellipsoid.clone(e._ellipsoid),d.createGeometry(e)}});
