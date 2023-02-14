define(["./when-54335d57","./Cartesian2-8646c5a1","./Transforms-79117a7b","./ComponentDatatype-1a100acd","./Check-24483042","./GeometryAttribute-374f805d","./GeometryAttributes-caa08d6c","./IndexDatatype-82ceea78","./Math-d6182036","./WallGeometryLibrary-0e5f04d3","./RuntimeError-88a32665","./WebGLConstants-95ceb4e9","./arrayRemoveDuplicates-3fea1e5f","./PolylinePipeline-3803a6c2","./EllipsoidGeodesic-cc216670","./EllipsoidRhumbLine-2b7999f3","./IntersectionTests-5394f658","./Plane-13ae4b1b"],function(v, C, H, A, e, b, k, w, G, L, i, t, a, n, r, o, s, l){"use strict";var x=new C.Cartesian3,P=new C.Cartesian3;function d(e){var i=(e=v.defaultValue(e,v.defaultValue.EMPTY_OBJECT)).positions,t=e.maximumHeights,a=e.minimumHeights,n=v.defaultValue(e.granularity,G.CesiumMath.RADIANS_PER_DEGREE),e=v.defaultValue(e.ellipsoid,C.Ellipsoid.WGS84);this._positions=i,this._minimumHeights=a,this._maximumHeights=t,this._granularity=n,this._ellipsoid=C.Ellipsoid.clone(e),this._workerName="createWallOutlineGeometry";i=1+i.length*C.Cartesian3.packedLength+2;v.defined(a)&&(i+=a.length),v.defined(t)&&(i+=t.length),this.packedLength=i+C.Ellipsoid.packedLength+1}d.pack=function(e, i, t){var a;t=v.defaultValue(t,0);var n=e._positions,r=n.length;for(i[t++]=r,a=0; a<r; ++a,t+=C.Cartesian3.packedLength)C.Cartesian3.pack(n[a],i,t);var o=e._minimumHeights,r=v.defined(o)?o.length:0;if(i[t++]=r,v.defined(o))for(a=0; a<r; ++a)i[t++]=o[a];var s=e._maximumHeights;if(r=v.defined(s)?s.length:0,i[t++]=r,v.defined(s))for(a=0; a<r; ++a)i[t++]=s[a];return C.Ellipsoid.pack(e._ellipsoid,i,t),i[t+=C.Ellipsoid.packedLength]=e._granularity,i};var u=C.Ellipsoid.clone(C.Ellipsoid.UNIT_SPHERE),p={positions:void 0,minimumHeights:void 0,maximumHeights:void 0,ellipsoid:u,granularity:void 0};return d.unpack=function(e, i, t){i=v.defaultValue(i,0);for(var a,n,r=e[i++],o=new Array(r),s=0; s<r; ++s,i+=C.Cartesian3.packedLength)o[s]=C.Cartesian3.unpack(e,i);if(0<(r=e[i++]))for(a=new Array(r),s=0; s<r; ++s)a[s]=e[i++];if(0<(r=e[i++]))for(n=new Array(r),s=0; s<r; ++s)n[s]=e[i++];var l=C.Ellipsoid.unpack(e,i,u),m=e[i+=C.Ellipsoid.packedLength];return v.defined(t)?(t._positions=o,t._minimumHeights=a,t._maximumHeights=n,t._ellipsoid=C.Ellipsoid.clone(l,t._ellipsoid),t._granularity=m,t):(p.positions=o,p.minimumHeights=a,p.maximumHeights=n,p.granularity=m,new d(p))},d.fromConstantHeights=function(e){var i=(e=v.defaultValue(e,v.defaultValue.EMPTY_OBJECT)).positions,t=e.minimumHeight,a=e.maximumHeight,n=v.defined(t),r=v.defined(a);if(n||r)for(var o=i.length,s=n?new Array(o):void 0,l=r?new Array(o):void 0,m=0; m<o; ++m)n&&(s[m]=t),r&&(l[m]=a);return new d({positions:i,maximumHeights:l,minimumHeights:s,ellipsoid:e.ellipsoid})},d.createGeometry=function(e){var i=e._positions,t=e._minimumHeights,a=e._maximumHeights,n=e._granularity,e=e._ellipsoid,t=L.WallGeometryLibrary.computePositions(e,i,a,t,n,!1);if(v.defined(t)){var r=t.bottomPositions,o=t.topPositions,s=o.length,n=2*s,l=new Float64Array(n),m=0;for(s/=3,c=0; c<s; ++c){var d=3*c,u=C.Cartesian3.fromArray(o,d,x),d=C.Cartesian3.fromArray(r,d,P);l[m++]=d.x,l[m++]=d.y,l[m++]=d.z,l[m++]=u.x,l[m++]=u.y,l[m++]=u.z}for(var t=new k.GeometryAttributes({position:new b.GeometryAttribute({componentDatatype:A.ComponentDatatype.DOUBLE,componentsPerAttribute:3,values:l})}),p=n/3,f=w.IndexDatatype.createTypedArray(p,n=2*p-4+p),h=0,c=0; c<p-2; c+=2){var g=c,y=c+2,_=C.Cartesian3.fromArray(l,3*g,x),E=C.Cartesian3.fromArray(l,3*y,P);C.Cartesian3.equalsEpsilon(_,E,G.CesiumMath.EPSILON10)||(_=c+3,f[h++]=E=c+1,f[h++]=g,f[h++]=E,f[h++]=_,f[h++]=g,f[h++]=y)}return f[h++]=p-2,f[h++]=p-1,new b.Geometry({attributes:t,indices:f,primitiveType:b.PrimitiveType.LINES,boundingSphere:new H.BoundingSphere.fromVertices(l)})}},function(e, i){return(e=v.defined(i)?d.unpack(e,i):e)._ellipsoid=C.Ellipsoid.clone(e._ellipsoid),d.createGeometry(e)}});
