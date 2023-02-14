define(["./when-54335d57","./Cartesian2-8646c5a1","./Check-24483042","./EllipsoidGeometry-8e9adb8a","./VertexFormat-81ec7207","./Math-d6182036","./GeometryOffsetAttribute-718fa138","./Transforms-79117a7b","./RuntimeError-88a32665","./ComponentDatatype-1a100acd","./WebGLConstants-95ceb4e9","./GeometryAttribute-374f805d","./GeometryAttributes-caa08d6c","./IndexDatatype-82ceea78"],function(i, a, e, o, n, t, r, s, c, d, l, m, u, p){"use strict";function y(e){var t=i.defaultValue(e.radius,1),e={radii:new a.Cartesian3(t,t,t),stackPartitions:e.stackPartitions,slicePartitions:e.slicePartitions,vertexFormat:e.vertexFormat};this._ellipsoidGeometry=new o.EllipsoidGeometry(e),this._workerName="createSphereGeometry"}y.packedLength=o.EllipsoidGeometry.packedLength,y.pack=function(e, t, r){return o.EllipsoidGeometry.pack(e._ellipsoidGeometry,t,r)};var G=new o.EllipsoidGeometry,f={radius:void 0,radii:new a.Cartesian3,vertexFormat:new n.VertexFormat,stackPartitions:void 0,slicePartitions:void 0};return y.unpack=function(e, t, r){t=o.EllipsoidGeometry.unpack(e,t,G);return f.vertexFormat=n.VertexFormat.clone(t._vertexFormat,f.vertexFormat),f.stackPartitions=t._stackPartitions,f.slicePartitions=t._slicePartitions,i.defined(r)?(a.Cartesian3.clone(t._radii,f.radii),r._ellipsoidGeometry=new o.EllipsoidGeometry(f),r):(f.radius=t._radii.x,new y(f))},y.createGeometry=function(e){return o.EllipsoidGeometry.createGeometry(e._ellipsoidGeometry)},function(e, t){return i.defined(t)&&(e=y.unpack(e,t)),y.createGeometry(e)}});
